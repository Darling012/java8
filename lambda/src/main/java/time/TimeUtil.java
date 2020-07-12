package time;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @program: java8
 * @description:
 * @author: Darling
 * @create: 2019-08-25 23:53
 **/
public class TimeUtil {
    public static void main(String[] args) {
        // TimeUtil timeUtil = new TimeUtil();
        // timeUtil.getPass24Hours().stream().forEach(System.out::println);
        Calendar time = getMidnight();
        // LocalDateTime localDateTime =
        // System.out.println();
    }

    private static Calendar getMidnight() {
        Calendar midnight = Calendar.getInstance();
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);
        return midnight;
    }

    private List<LocalDateTime> getPass24Hours() {
        List<LocalDateTime> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime pass = now.minusHours(24);
        while (pass.isBefore(now)) {
            result.add(pass);
            pass = pass.plusHours(1);
        }
        return result;
    }
}
