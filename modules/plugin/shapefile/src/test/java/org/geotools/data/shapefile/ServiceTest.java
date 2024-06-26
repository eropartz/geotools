/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.data.shapefile;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.geotools.TestData;
import org.geotools.api.data.DataStore;
import org.geotools.api.data.DataStoreFactorySpi;
import org.geotools.api.data.DataStoreFinder;
import org.junit.Test;

/**
 * @version $Id$
 * @author ian
 */
public class ServiceTest extends TestCaseSupport {

    final String TEST_FILE = "shapes/statepop.shp";

    /** Make sure that the loading mechanism is working properly. */
    @Test
    public void testIsAvailable() {
        Iterator<DataStoreFactorySpi> list = DataStoreFinder.getAvailableDataStores();
        boolean found = false;
        while (list.hasNext()) {
            DataStoreFactorySpi fac = list.next();
            if (fac instanceof ShapefileDataStoreFactory) {
                found = true;
                assertNotNull(fac.getDescription());
                break;
            }
        }
        assertTrue("ShapefileDataSourceFactory not registered", found);
    }

    /** Ensure that we can create a DataStore using url OR string url. */
    @Test
    public void testShapefileDataStore() throws Exception {
        Map<String, Serializable> params = new HashMap<>();
        params.put("url", TestData.url(TEST_FILE));
        DataStore ds = DataStoreFinder.getDataStore(params);
        assertNotNull(ds);
        params.put("url", TestData.url(TEST_FILE).toString());
        assertNotNull(ds);
        ds.dispose();
    }

    @Test
    public void testBadURL() {
        Map<String, Serializable> params = new HashMap<>();
        params.put("url", "aaa://bbb.ccc");
        try {
            ShapefileDataStoreFactory f = new ShapefileDataStoreFactory();
            f.createDataStore(params);
            fail("did not throw error");
        } catch (java.io.IOException ioe) {
            // this is actually good
        }
    }
}
