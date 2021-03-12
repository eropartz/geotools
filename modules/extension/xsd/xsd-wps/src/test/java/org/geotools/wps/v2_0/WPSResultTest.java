package org.geotools.wps.v2_0;

import junit.framework.TestCase;
import net.opengis.wps20.DataOutputType;
import net.opengis.wps20.GetResultType;
import net.opengis.wps20.ResultType;
import org.geotools.xsd.Encoder;
import org.geotools.xsd.Parser;
import org.junit.Assert;

public class WPSResultTest extends TestCase {

    public void testParseResultRequest() throws Exception {
        WPSConfiguration wps = new WPSConfiguration();
        Parser parser = new Parser(wps);

        Object o = parser.parse(getClass().getResourceAsStream("wpsResultRequestExample.xml"));
        assertTrue(o instanceof GetResultType);

        GetResultType resultType = (GetResultType) o;
        assertEquals("FB6DD4B0-A2BB-11E3-A5E2-0800200C9A66", resultType.getJobID());

        Encoder encoder = new Encoder(wps);
        String encodedValue = encoder.encodeAsString(o, WPS.GetResult);

        Assert.assertTrue(
                encodedValue.contains(
                        "<wps:JobID>FB6DD4B0-A2BB-11E3-A5E2-0800200C9A66</wps:JobID>"));
    }

    public void testParseResultResponse() throws Exception {
        WPSConfiguration wps = new WPSConfiguration();
        Parser parser = new Parser(wps);

        Object o = parser.parse(getClass().getResourceAsStream("wpsResultResponseExample.xml"));
        assertTrue(o instanceof ResultType);

        ResultType resultType = (ResultType) o;
        assertEquals("FB6DD4B0-A2BB-11E3-A5E2-0800200C9A66", resultType.getJobID());

        Assert.assertNotNull(resultType.getExpirationDate());
        Assert.assertNotNull(resultType.getOutput());
        Assert.assertEquals(1, resultType.getOutput().size());
        DataOutputType output = resultType.getOutput().get(0);

        Assert.assertEquals("BUFFERED_GEOMETRY", output.getId());

        Assert.assertNotNull(output.getReference());
        Assert.assertEquals(
                "http://result.data.server/FB6DD4B0-A2BB-11E3-A5E2-0800200C9A66/BUFFERED_GEOMETRY.xml",
                output.getReference().getHref());

        Encoder encoder = new Encoder(wps);
        String encodedValue = encoder.encodeAsString(o, WPS.Result);

        Assert.assertTrue(
                encodedValue.contains(
                        "<wps:JobID>FB6DD4B0-A2BB-11E3-A5E2-0800200C9A66</wps:JobID>"));
        Assert.assertTrue(
                encodedValue.contains(
                        "http://result.data.server/FB6DD4B0-A2BB-11E3-A5E2-0800200C9A66/BUFFERED_GEOMETRY.xml"));
    }
}
