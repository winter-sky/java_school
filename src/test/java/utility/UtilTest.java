package utility;

import mainpackage.util.Util;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UtilTest {
    @Test
    public void testTimeUtil() throws Exception {
        // Some moment of time on Nov 2018.
        long time = 1541929528098L;

        System.out.println("Time: " + new Date(time));

        Timestamp ts = Util.getMonthStart(time);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");

        Date expected = df.parse("20181101-00:00:00.000");

        assertEquals("Unexpected month start date [expected=" + expected + ", actual=" + ts + ']',
        0, ts.compareTo(expected));
    }
}
