
package test.bitmap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadBitmap {
    private static final int IO_BUFFER_SIZE = 1024*8;

    //http://clw.17car.com.cn/genCode.action
    /**
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("c:/test.png");
        FileOutputStream outputStream = new FileOutputStream(file); 
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        String urlString = "http://clw.17car.com.cn/genCode.action";
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
//            return true;
        } catch (final IOException e) {
//            Log.e(TAG, "Error in downloadBitmap - " + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {}
        }
//        return false;

    }
}
