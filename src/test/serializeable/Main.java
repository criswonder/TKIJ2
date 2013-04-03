
package test.serializeable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author javadb.com
 */
public class Main {

    /**
     * Example method for using the ObjectOutputStream class
     */
    public void writePersons(String filename) {

        ObjectOutputStream outputStream = null;
        try {

            // Construct the LineNumberReader object
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));

            Person person = new Person();
            person.setFirstName("James");
            person.setLastName("Ryan");
            person.setAge(19);

            outputStream.writeObject(person);

            person = new Person();

            person.setFirstName("Obi-wan");
            person.setLastName("Kenobi");
            person.setAge(30);

            outputStream.writeObject(person);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            // Close the ObjectOutputStream
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        writeObject();

        readObject();
        
        readAndWriteDataInAndroid();
    }

    /**
     * android 里面测试使用
     */
    private static void readAndWriteDataInAndroid() {
        
        
//        FileInputStream fis;
//        try {
//            fis = openFileInput("orderData");
//            ObjectInputStream iis = new ObjectInputStream(fis);
//            OrderData emp = (OrderData) iis.readObject();
//            if(emp!=null) {
//                orderData = emp;
//                return;
//            }
//            System.out.println(emp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//
//
//
//        
//        try {
//            FileOutputStream outputStream = openFileOutput("orderData", MODE_PRIVATE );
//            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
//            oos.writeObject(orderData);
//        } catch ( Exception e) {
//            e.printStackTrace();
//        }
    }

    private static void writeObject() {
        File file = new File("myFile1.txt");
        System.out.println(file.getAbsolutePath());
        new Main().writePersons("myFile.txt");
    }

    private static void readObject() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("myFile.txt");
            ObjectInputStream iis = new ObjectInputStream(fis);
            Person emp = (Person) iis.readObject();
            System.out.println(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
}