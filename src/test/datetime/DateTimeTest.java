
package test.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * yyyy-MM-dd HH:mm:ss 中各个字母的含义见
 * http://blog.csdn.net/xiaojianpitt/article/details/2127731
 * @author Andy Mao
 *
 */
public class DateTimeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        System.out.println(format.format(date));
        System.out.println(format1.format(date));
    }
 
}
