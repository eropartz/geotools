package org.geotools.data.teradata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Handler;
import java.util.logging.Level;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.filter.Filter;
import org.geotools.api.filter.spatial.BBOX;
import org.geotools.api.data.Query;
import org.geotools.api.data.Transaction;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.jdbc.AutoGeneratedPrimaryKeyColumn;
import org.geotools.jdbc.JDBCTestSetup;
import org.geotools.jdbc.JDBCTestSupport;
import org.geotools.jdbc.PrimaryKeyColumn;
import org.geotools.util.logging.Logging;
import org.junit.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

/**
 * Test the LOB workaround and triggers directly.
 *
 * @author Ian Schneider
 */
public class TeradataDialectOnlineTest extends JDBCTestSupport {
    static int cnt = 99;

    static void enableLogging(Level level) {
        Handler handler = Logging.getLogger(TeradataDialectOnlineTest.class).getHandlers()[0];
        handler.setLevel(level);

        Logging.getLogger(TeradataDialectOnlineTest.class).setLevel(level);
    }

    @Override
    protected void setUpInternal() throws Exception {
        super.setUpInternal();
        enableLogging(Level.INFO);
    }

    @Override
    protected void tearDownInternal() throws Exception {
        super.tearDownInternal();
    }

    @Override
    protected JDBCTestSetup createTestSetup() {
        return new DialectTestSetup();
    }

    @Test
    public void testLOB() throws Exception {
        insertGeom(16000);
        insertGeom(30000);
        insertGeom(60000);
        insertGeom(120000);
        assertInline(cnt - 4, true, true, false, false);
    }

    @Test
    public void testSmallWKT() throws Exception {
        int coords = insertGeom(16000);
        read(coords, cnt - 1, true);
    }

    @Test
    public void testLargeWKT() throws Exception {
        int coords = insertGeom(60000);
        read(coords, cnt - 1, false);
    }

    // this currently doesn't exercise the indexing since tessalation doesn' exist
    @Test
    public void testLargerWKTBBox() throws Exception {
        enableLogging(Level.FINE);
        int coords = insertGeom(30000);
        BBOX bbox =
                CommonFactoryFinder.getFilterFactory(null)
                        .bbox("geometry", -181.8, -90.868, 181.8, 84.492, null);
        read(coords, cnt - 1, bbox, true);
    }

    private int insertGeom(int size) throws SQLException {
        StringBuilder geom = new StringBuilder("LINESTRING(");
        int coords = 0;
        while (geom.length() < size) {
            if (coords > 0) {
                geom.append(',');
            }
            double coord = -90 + (coords % 90);
            geom.append(' ');
            geom.append(coord);
            geom.append(' ');
            geom.append(coord);
            coords++;
        }
        geom.append(")");
        try (Connection conn = dataStore.getDataSource().getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(
                                "INSERT INTO \"ft3\" VALUES(?,new ST_Geometry(?),0,0.0,'zero')")) {
            ps.setInt(1, cnt++);
            ps.setCharacterStream(2, new StringReader(geom.toString()), geom.length());
            ps.execute();
        }
        return coords;
    }

    private void read(int size, int id, boolean expectInline) throws Exception {
        read(size, id, Filter.INCLUDE, expectInline);
    }

    private void read(int size, int id, Filter f, boolean expectInline) throws Exception {
        final String fid = "ft3." + id;
        SimpleFeatureSource featureSource = dataStore.getFeatureSource("ft3");
        Query q = new Query();
        q.setFilter(f);
        Geometry g = null;
        try (SimpleFeatureIterator features = featureSource.getFeatures(q).features()) {
            while (features.hasNext()) {
                SimpleFeature next = features.next();
                if (next.getID().equals(fid)) {
                    g = (Geometry) next.getDefaultGeometry();
                    break;
                }
            }
        }
        assertNotNull("could not locate " + fid, g);
        assertEquals(size, g.getCoordinates().length);

        // verify autoconnect set by dialog
        try (Connection connection = dataStore.getConnection(Transaction.AUTO_COMMIT)) {
            assertTrue(connection.getAutoCommit());
        }

        // and LOB workaround
        assertInline(id, expectInline);
    }

    private void assertInline(final int startIdx, boolean... expectInline) throws Exception {
        SimpleFeatureSource featureSource = dataStore.getFeatureSource("ft3");
        // verify dialect encodes LOB workadound and reads correctly
        StringBuffer buf = new StringBuffer("select id, geometry");
        dialect.encodePostSelect(featureSource.getSchema(), buf);
        // ensure order by or ids aren't in sequence
        buf.append(" from ft3 order by id");
        try (Connection connection = dataStore.getConnection(Transaction.AUTO_COMMIT);
                Statement s = connection.createStatement();
                ResultSet rs = s.executeQuery(buf.toString())) {
            // geometry column is always Clob
            assertEquals("java.sql.Clob", rs.getMetaData().getColumnClassName(2));
            // geometry_inline derived column is String
            assertEquals("java.lang.String", rs.getMetaData().getColumnClassName(3));

            // make sure that starting from startIdx we get an inline String or not
            for (boolean b : expectInline) {
                assertTrue(rs.next());
                String result = rs.getString(3);
                if (b) {
                    assertNotNull(result);
                    new WKTReader().read(result);
                } else {
                    assertNull(result);
                }
            }
        }
    }

    static class DialectTestSetup extends TeradataTestSetup {
        @Override
        protected void setUpData() throws Exception {
            super.setUpData();
            enableLogging(Level.FINE);
            runSafe("DELETE FROM SYSSPATIAL.GEOMETRY_COLUMNS WHERE F_TABLE_NAME = 'ft3'");
            runSafe("DROP TRIGGER \"ft3_geometry_mi\"");
            runSafe("DROP TRIGGER \"ft3_geometry_mu\"");
            runSafe("DROP TRIGGER \"ft3_geometry_md\"");
            runSafe("DROP HASH INDEX \"ft3_geometry_idx_idx\"");
            runSafe("DROP TABLE \"ft3_geometry_idx\"");
            runSafe("DROP TABLE \"ft3\"");

            run(
                    "CREATE TABLE \"ft3\"(" //
                            + "\"id\" PRIMARY KEY not null integer, " //
                            + "\"geometry\" ST_GEOMETRY, " //
                            + "\"intProperty\" int," //
                            + "\"doubleProperty\" double precision, " //
                            + "\"stringProperty\" varchar(200) casespecific)");
            run(
                    "INSERT INTO SYSSPATIAL.GEOMETRY_COLUMNS VALUES('"
                            + fixture.getProperty("database")
                            + "', '"
                            + fixture.getProperty("schema")
                            + "', 'ft3', 'geometry', 2, "
                            + srid4326
                            + ", 'LINESTRING',-180,-90,180,90)");
            // @todo when things are fixed on teradata side of things, add back primary index
            run(
                    "CREATE MULTISET TABLE \"ft3_geometry_idx\""
                            + " (id INTEGER NOT NULL, cellid INTEGER NOT NULL) PRIMARY INDEX (id)");
            //                    + " (id INTEGER NOT NULL, cellid INTEGER NOT NULL)");
            run(
                    "CREATE HASH INDEX ft3_geometry_idx_idx (cellid) ON ft3_geometry_idx ORDER BY (cellid);");
            TeradataDialect d = new TeradataDialect(null);
            PrimaryKeyColumn col = new AutoGeneratedPrimaryKeyColumn("id", null);
            d.installTriggers(
                    getDataSource().getConnection(),
                    "ft3",
                    "geometry",
                    "ft3_geometry_idx",
                    Arrays.asList(col));

            runSafe("DELETE FROM sysspatial.tessellation WHERE f_table_name = 'ft3'");
            run(
                    "INSERT INTO sysspatial.tessellation VALUES ("
                            + "'geotools',"
                            + "'ft3',"
                            + "'geometry',"
                            + "-180,-90,180,90,"
                            + "1000,1000,3,.01,0"
                            + ")");
        }
    }
}
