
package test.socketFile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {   
    private Socket client;   
    private boolean connected;   
       
    public boolean isConnected() {   
        return connected;   
    }   
  
    public void setConnected(boolean connected) {   
        this.connected = connected;   
    }   
  
    public Client(String host, int port){   
        try {   
            //1、新建Socket对象   
            client = new Socket(host, port);   
            System.out.println("服务器连接成功！");   
               
            this.connected = true;   
               
        } catch (UnknownHostException e) {   
            this.connected = false;   
            close();   
        } catch (IOException e) {   
            System.out.println("服务器连接失败！");   
            this.connected = false;   
            close();   
        }   
    }   
       
    /**  
     * 将文件内容发送出去  
     *   
     * @param filepath 文件的全路径名  
     */  
    public void sendFile(String filepath){   
        DataOutputStream out = null;   
        DataInputStream reader = null;   
        try{   
            if(client == null) return;   
  
            File file = new File(filepath);   
            reader = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));   
               
            //2、将文件内容写到Socket的输出流中   
            out = new DataOutputStream(client.getOutputStream());   
               
            out.writeUTF(file.getName()); //附带文件名   
               
            int bufferSize = 2048; //2K   
            byte[] buf = new byte[bufferSize];   
               
            int read = 0;   
            while((read=reader.read(buf)) != -1){   
                out.write(buf, 0, read);   
            }   
               
            out.flush();   
               
        }catch(IOException ex){   
            ex.printStackTrace();   
            close();   
        }finally{   
            try {   
                reader.close();   
                out.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
    }   
       
    /**  
     * 关闭Socket  
     */  
    public void close(){   
        if(client != null){   
            try {   
                client.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
    }   
       
    public static void main(String[] args) {   
        Client client = new Client(Server.host, Server.port);   
        long start = System.currentTimeMillis();
        if(client.isConnected()){   
            client.sendFile("E:\\goagent.zip");   
            client.close();   
        }  
        System.out.println(System.currentTimeMillis() - start);
    }   
       
}  



