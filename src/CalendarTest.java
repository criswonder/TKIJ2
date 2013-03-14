

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		test1();
//	    weatherAppCalculateDayofWeek();
	    Calendar calendar = Calendar.getInstance(Locale.CHINESE);
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int day =calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(y);
        System.out.println(m+1);
        System.out.println(day);
	}
	/**
	 * 天气预报中 需要计算今天后的第三天，第四天 是星期几
	 */
	private static void weatherAppCalculateDayofWeek() {
	    Calendar cal = Calendar.getInstance(Locale.CHINA);
        Date today = new Date();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, 2);
        printTime(cal);
        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
	}
    private static void test1() {
        String str;
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		str = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
		System.out.println(str);
		
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		int n = -1;
		cal.add(Calendar.DATE, n * 7);
		// 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		str = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		System.out.println(str);
		
		cal = Calendar.getInstance();
		n = -2;
		cal.add(Calendar.DATE, n * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		str = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		System.out.println(str);

		//获得前一个月的某天
		cal = Calendar.getInstance(Locale.CHINA);
		cal.add(Calendar.MONTH, -1);
		printTime(cal);
		cal.set(Calendar.DAY_OF_WEEK, 3); //add(f, delta) 参数3这里是表示在上个 月的同一号 再加三天
		printTime(cal);
		//获得前二个月的某天
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		printTime(cal);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		printTime(cal);
		
		//获得某一天后的几天
		cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -2);
		Date date1 = new Date();
		cal.getTime().compareTo(date1);
		printTime(cal);
    }
	
	public static void printTime(Calendar cal){
		String str = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		System.out.println(str);
	}
}
