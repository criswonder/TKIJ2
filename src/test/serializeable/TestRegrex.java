
package test.serializeable;

public class TestRegrex {

    public static void main(String[] args) {
        String phoneNum = "^[1][3-8]\\d{9}$";
        String phone1 = "15088600372";
        String phone2 = "25088600372";
        String phone3 = "150886003";
        String phone4 = "155886003543";
        String phone5 = "13812345678";
        String phone6 = "234xxxxxxxx";
        String phone7 = "xxxxxxxxxxxxx";
        String phone8 = "#@!@#!sfsdf2";
        
        
        System.out.println(phone1.matches(phoneNum));
        System.out.println(phone2.matches(phoneNum));
        System.out.println(phone3.matches(phoneNum));
        System.out.println(phone4.matches(phoneNum));
        System.out.println(phone5.matches(phoneNum));
        System.out.println(phone6.matches(phoneNum));
        System.out.println(phone7.matches(phoneNum));
        System.out.println(phone8.matches(phoneNum));
    }
}
