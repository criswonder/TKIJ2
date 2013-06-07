
package test.socketFile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread{   
    public static int port = 8888;   
    public static String host = "localhost";   
    private static ServerSocket server = null;   
       
    public void run() {   
        if(server == null){   
            try{   
                //1、新建ServerSocket实例   
                server = new ServerSocket(port);   
            }catch(IOException e){   
                e.printStackTrace();   
            }   
        }   
           
        System.out.println("服务器启动...");   
        while(true){   
            try{   
                //2、访问ServerSocket实例的accept方法取得一个客户端Socket对象   
                Socket client = server.accept();   
                if(client == null) continue;   
                   
                new SocketConnection(client, "D:\\").start();   
                   
            }catch(IOException ex){   
                ex.printStackTrace();   
            }   
        }   
    }   
  
    public static void main(String[] args) {   
        new Server().start();   
    }   
       
}  


