
package test.file;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FormUploads {

    public static void main(String[] args) throws Exception {
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File temp_file = new File("F:/desert.jpg");
        String urlString = "http://clw.17car.com.cn/car/reportcase.action?userid=3&carid=2&jingdu=100&weidu=46";
        
        // open a URL connection
        URL url = new URL(urlString );

        // Open a HTTP connection to the URL
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Allow Inputs
        conn.setDoInput(true);

        // Allow Outputs
        conn.setDoOutput(true);

        // Don't use a cached copy.
        conn.setUseCaches(false);

        // Use a post method.
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
//
//        // Send parameter #1
//        dos.writeBytes(twoHyphens + boundary + lineEnd);
//        dos.writeBytes("Content-Disposition: form-data; name=\"param1\"" + lineEnd + lineEnd);
//        dos.writeBytes("foo1" + lineEnd);
//
//        // Send parameter #2
//        dos.writeBytes(twoHyphens + boundary + lineEnd);
//        dos.writeBytes("Content-Disposition: form-data; name=\"param2\"" + lineEnd + lineEnd);
//        dos.writeBytes("foo2" + lineEnd);
//
//        // Send a binary file
//        dos.writeBytes(twoHyphens + boundary + lineEnd);
        dos.writeBytes("Content-Disposition: form-data; name=\"files\";filename=\"" + temp_file.getName() + "\"" + lineEnd);
        dos.writeBytes(lineEnd);

//        File file = new File("abc");
        // create a buffer of maximum size
        FileInputStream fileInputStream = new FileInputStream(temp_file );
        bytesAvailable = fileInputStream.available();
        bufferSize = Math.min(bytesAvailable, maxBufferSize);
        buffer = new byte[bufferSize];

        // read file and write it into form...

        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0) {
            dos.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        // send multipart form data necesssary after file data...
        dos.writeBytes(lineEnd);
        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

        // close streams
        fileInputStream.close();
        dos.flush();
        dos.close();
    }
}
