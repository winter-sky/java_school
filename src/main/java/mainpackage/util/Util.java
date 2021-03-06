package mainpackage.util;

import mainpackage.dto.ParamDTO;
import mainpackage.model.Params;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.*;

public class Util {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);

        bd = bd.setScale(places, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public static Timestamp getMonthStart(long currentTime) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(currentTime);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getWeekStart(long currentTime) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(currentTime);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return new Timestamp(cal.getTimeInMillis());
    }

    public static ArrayList<String> removeDuplicates(List<String> listAuthors) {
        return new ArrayList<>(new HashSet<>(listAuthors));
    }
}
