package time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，修改这些对象对象会返回一个副本
 */
public class DateTest {

    @Test
    public void getCurrentTime() {
        //获取当前时间
        LocalTime time = LocalTime.now();
        System.out.println("local time now : " + time);
    }

    @Test
    public void getCurrentDate() {
        //获取今天的日期
        LocalDate today = LocalDate.now();
        System.out.println(today);
        //这个是作为对比
        Date date = new Date();
        System.out.println(date);
    }

    @Test
    public void getDetailDate() {
        //获取年、月、日、星期几信息
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int year1 = today.get(ChronoField.YEAR);
        System.out.println(year1);
        int month = today.getMonthValue();
        int month1 = today.get(ChronoField.MONTH_OF_YEAR);
        int day = today.getDayOfMonth();
        int day1 = today.get(ChronoField.DAY_OF_MONTH);
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        int dayOfWeek1 = today.get(ChronoField.DAY_OF_WEEK);
        System.out.printf("Year : %d  Month : %d  day : %d t %n", year, month, day);
    }

    @Test
    public void getHourMinuteSecond() {
        LocalTime localTime = LocalTime.of(13, 51, 10);
        LocalTime localTime1 = LocalTime.now();
        //获取小时
        int hour = localTime.getHour();
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        //获取分
        int minute = localTime.getMinute();
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        //获取秒
        int second = localTime.getSecond();
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        LocalDateTime localDateTime4 = localTime.atDate(localDate);
        // 获取LocalDate
        LocalDate localDate2 = localDateTime.toLocalDate();
        //    获取LocalTime
        LocalTime localTime2 = localDateTime.toLocalTime();
    }

    @Test
    public void testInstant() {
        // 时间戳
        Instant instant = Instant.now();
        //     获取秒数
        long currentSecond = instant.getEpochSecond();
        //     获取毫秒数
        long currentMilli = instant.toEpochMilli();
        System.currentTimeMillis();

        LocalDateTime dateTime = LocalDateTime.of(2020, 9, 2, 10, 47, 00);
        System.out.println(dateTime);
        System.out.println(dateTime.toEpochSecond(ZoneOffset.of("+8")));


        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(1599014820), ZoneId.of("+8")));
        System.out.println(str);
    }


    @Test
    public void handleSpecialDate() {
        //处理特定日期
        LocalDate dateOfBirth = LocalDate.of(2018, 01, 21);
        System.out.println("The specil date is : " + dateOfBirth);
    }

    @Test
    public void compareDate() {
        //判断两个日期是否相等
        LocalDate today = LocalDate.now();
        LocalDate date1 = LocalDate.of(2018, 01, 21);
        if (date1.equals(today)) {
            System.out.printf("TODAY %s and DATE1 %s are same date %n", today, date1);
        }
    }

    @Test
    public void cycleDate() {
        //处理周期性的日期
        LocalDate today = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(2018, 01, 21);

        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);

        if (currentMonthDay.equals(birthday)) {
            System.out.println("Many Many happy returns of the day !!");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }
    }


    @Test
    public void plusHours() {
        //增加小时
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(2); // 增加两小时
        System.out.println("Time after 2 hours : " + newTime);
    }

    @Test
    public void nextWeek() {
        //计算一周后的日期
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 week : " + nextWeek);
    }

    @Test
    public void minusDate() {
        //   计算一年前或一年后的日期
        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year : " + previousYear);
        today.minusYears(1);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Date after 1 year : " + nextYear);
        // 通过with修改某些值
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withYear(2020);
        //修改为2022
        localDateTime = localDateTime.with(ChronoField.YEAR, 2022);
    }

    @Test
    public void getMonthlyFirstAndEnd() {
        LocalDate today = LocalDate.now();
        //本月的第一天
        LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth(), 1);
        //本月的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月的第一天" + firstday);
        System.out.println("本月的最后一天" + lastDay);
    }

    @Test
    public void clock() {
        // 根据系统时间返回当前时间并设置为UTC。
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);

        // 根据系统时钟区域返回时间
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + clock);
    }

    @Test
    public void isBeforeOrIsAfter() {
        //如何用Java判断日期是早于还是晚于另一个日期
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2018, 1, 29);
        if (tomorrow.isAfter(today)) {
            System.out.println("Tomorrow comes after today");
        }

        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);

        if (yesterday.isBefore(today)) {
            System.out.println("Yesterday is day before today");
        }
    }

    @Test
    public void getZoneTime() {
        //时区处理
        //设置时区
        ZoneId america = ZoneId.of("America/New_York");

        LocalDateTime localtDateAndTime = LocalDateTime.now();

        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("现在的日期和时间在特定的时区 : " + dateAndTimeInNewYork);
    }

    @Test
    public void checkCardExpiry() {
        //使用 YearMonth类处理特定的日期
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());

        YearMonth creditCardExpiry = YearMonth.of(2028, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }

    @Test
    public void isLeapYear() {
        //检查闰年
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println("2018 is not a Leap year");
        }
        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.isLeapYear());
    }

    @Test
    public void calcDateDays() {
        //计算两个日期之间的天数和月数
        LocalDate today = LocalDate.now();
        LocalDate java8Release = LocalDate.of(2018, Month.MAY, 14);
        Period period = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release : "
                                   + period.getMonths());
        period.getDays();
    }

    @Test
    public void calcDateSeconds() {
        // 计算两个时间之间的秒数
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(dtf));
        LocalDateTime future = now.minusMinutes(1);
        System.out.println(future.format(dtf));
        // Integer startNum = Math.toIntExact(Duration.between(future, now).toSeconds());
        System.out.println(Math.toIntExact(Duration.between(future, now)
                                                   .toMillis() / 1000));
        // System.out.println(startNum);
    }

    @Test
    public void ZoneOffset() {
        // 包含时差信息的日期和时间
        LocalDateTime datetime = LocalDateTime.of(2018, Month.FEBRUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Date and Time with timezone offset in Java : " + date);
    }


    @Test
    public void formatDate() {
        // 使用预定义的格式化工具去解析或格式化日期
        String dayAfterTomorrow = "20180210";
        LocalDate formatted = LocalDate.parse(dayAfterTomorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", dayAfterTomorrow, formatted);
        LocalDate localDate1 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate localDate2 = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);

        LocalDate localDate = LocalDate.of(2019, 9, 10);
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        //自定义格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = localDate.format(dateTimeFormatter);


    }

    @Test
    public void getCurrentMillis() {
        System.out.println(System.currentTimeMillis());
        // 时间戳
        //获取秒数
        Long second = LocalDateTime.now()
                                   .toEpochSecond(ZoneOffset.of("+8"));
        //获取毫秒数
        Long milliSecond = LocalDateTime.now()
                                        .toInstant(ZoneOffset.of("+8"))
                                        .toEpochMilli();

        System.out.println(second);
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(1599463500), ZoneId.of("+8")));
        System.out.println(str);
    }

    @Test
    public void getZone() {
        // 获取所有时区
        Set<String> set = ZoneId.getAvailableZoneIds();
        Set<String> treeSet = new TreeSet<String>() {
            {
                addAll(set);
            }
        };
        treeSet.forEach(System.out::println);

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);
    }
}
