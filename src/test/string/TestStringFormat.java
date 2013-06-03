
package test.string;

import java.io.UnsupportedEncodingException;

public class TestStringFormat {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Double d = 12.3366;
        
        System.out.println(String.format("%2$08d", -3123,-5566));
        System.out.println(String.format("%1$9d", -31));
        System.out.println(String.format("%1$-9d", -31));
        System.out.println(String.format("%1$(9d", -31));
        System.out.println(String.format("%1$#9x", 5689));
        
        //小数点后面两位
        System.out.println(String.format("%1$.2f", 5689.0)); //必须是同类型的才能进行转换
        
        //格式化的位置
        /*String str = "I love ni %s, you love me %s";
        String str2 = "I love ni %2$s, you love me %1$s";
        
        System.out.println(String.format(str, "renjunjie","songliyu"));
        
        System.out.println(String.format(str2, "renjunjie","songliyu"));*/
        
        
        //数组的操作
        Object[] sendData = new Object[4];
        sendData[0] = Integer.valueOf(1);
        sendData[1] = "172.12.1.2";
        sendData[2] = Integer.valueOf(123);
        sendData[3] = "testadfaerfa";
        String sendDataString = String.format("%d,%s,%d,%s",(Object[]) sendData);
        System.out.println(sendDataString);
        
        String out = String.format("%3s %s天", "2012-12-12 22","2");
        System.out.println(out);
        
        String s1 = "汉字";
        String s2;
        try {
            s2 = String.format("%6s",new String(s1.getBytes(),"ISO-8859-1"));
            s2 = new String(s2.getBytes("ISO-8859-1"));
            System.out.println(s2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String   str = String.format("%.2f ",4545.421111);
        System.out.println(str);
    }
}
