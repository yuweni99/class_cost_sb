package com.zys.class_cost_sb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
    private DateUtils() {
    }

    /**
     * 日期格式
     **/
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String format(Object date) {
        return format(date, DATE_PATTERN.YYYY_MM_DD);
    }

    public static final Date parse(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final Date parse(String dateStr) {
        return parse(dateStr, DATE_PATTERN.YYYY_MM_DD);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String format(Object date, String pattern) {
        if (date == null) {
            return null;
        }
        if (pattern == null) {
            return format(date);
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取日期
     *
     * @return
     */
    public static final String getDate() {
        return format(new Date());
    }

    /**
     * 获取日期时间
     *
     * @return
     */
    public static final String getDateTime() {
        return format(new Date(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取日期
     *
     * @param pattern
     * @return
     */
    public static final String getDateTime(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 日期计算
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static final Date addDate(Date date, int field, int amount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 字符串转换为日期:不支持yyM[M]d[d]格式
     *
     * @param date
     * @return
     */
    public static final Date stringToDate(String date) {
        if (date == null) {
            return null;
        }
        String separator = String.valueOf(date.charAt(4));
        String pattern = "yyyyMMdd";
        if (!separator.matches("\\d*")) {
            pattern = "yyyy" + separator + "MM" + separator + "dd";
            if (date.length() < 10) {
                pattern = "yyyy" + separator + "M" + separator + "d";
            }
        } else if (date.length() < 8) {
            pattern = "yyyyMd";
        }
        pattern += " HH:mm:ss.SSS";
        pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getDayBetween(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        long n = end.getTimeInMillis() - start.getTimeInMillis();
        return (int) (n / (60 * 60 * 24 * 1000l));
    }

    /**
     * 间隔月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getMonthBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || !startDate.before(endDate)) {
            return null;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int year1 = start.get(Calendar.YEAR);
        int year2 = end.get(Calendar.YEAR);
        int month1 = start.get(Calendar.MONTH);
        int month2 = end.get(Calendar.MONTH);
        int n = (year2 - year1) * 12;
        n = n + month2 - month1;
        return n;
    }

    /**
     * 间隔月，多一天就多算一个月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || !startDate.before(endDate)) {
            return null;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int year1 = start.get(Calendar.YEAR);
        int year2 = end.get(Calendar.YEAR);
        int month1 = start.get(Calendar.MONTH);
        int month2 = end.get(Calendar.MONTH);
        int n = (year2 - year1) * 12;
        n = n + month2 - month1;
        int day1 = start.get(Calendar.DAY_OF_MONTH);
        int day2 = end.get(Calendar.DAY_OF_MONTH);
        if (day1 <= day2) {
            n++;
        }
        return n;
    }

    /**
     * 格式化时间差
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String cgformat(Date date) throws ParseException {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String res = "";
        // 相差天数
        int day = DateUtils.plusDay(now, calendar.getTime());
        switch (day) {
            case 0:
                int minute = plus(now, date);
                if (minute > 180) {
                    res = convertDateToString(date, "HH:mm");
                } else if (minute > 120) {
                    res = "2小时前";
                } else if (minute > 90) {
                    res = "1.5小时前";
                } else if (minute > 60) {
                    res = "1小时前";
                } else if (minute > 30) {
                    res = "半小时前";
                } else if (minute > 25) {
                    res = "25分钟前";
                } else if (minute > 15) {
                    res = "15分钟前";
                } else if (minute > 5) {
                    res = "5分钟前";
                } else if (minute > 3) {
                    res = "3分钟前";
                } else {
                    res = "刚刚";
                }
                break;
//		case 1:
//			res = convertDateToString(date, "HH:mm");
//			break;
//		case 2:
//			res = convertDateToString(date, "HH:mm");
//			break;
            default:
                res = day + "天前";
                break;
        }
        return res;
        //
        // int date = calendar.get(Calendar.DATE);
        // if(calendar.get(Calendar.DATE))
        // Date nowD = convertStringToDate(convertDateToString("yyyy-MM-dd"),
        // "yyyy-MM-dd");
        // long cha = date.getTime() - nowD.getTime();
        // if (cha > 0) {
        // return "今天" + convertDateToString(date, "H") + "点";
        // } else {
        // if (Math.abs(cha) < 24L * ONE_HOUR) {
        // return "昨天" + convertDateToString(date, "H") + "点";
        // } else {
        // return convertDateToString(date, "yyyy-MM-dd HH:mm:ss");
        // }
        // }
    }

    /**
     * @return Integer
     * @throws
     * @Method: plus
     * @Description: 求两个时间类型的分钟差
     */
    public static int plus(Date date1, Date date2) {
        Long diff = date1.getTime() - date2.getTime();
        Long days = diff / (1000 * 60);
        return days.intValue();
    }

    /**
     * 两个时间的间隔天数
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int plusDay(Date date1, Date date2) throws ParseException {
        Long diff = date1.getTime() - date2.getTime();
        Long days = diff / (1000 * 3600 * 24);
        return days.intValue();
    }

    /**
     * @param date   日期
     * @param format 格式
     * @return
     * @throws
     * @Method: convertDateToString
     * @Description: 获取特定格式的日期字符串
     */
    public static String convertDateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String dateAsString = dateFormat.format(date);
        return dateAsString;
    }
}
