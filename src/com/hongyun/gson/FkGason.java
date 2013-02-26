
package com.hongyun.gson;

import com.google.gson.Gson;

public class FkGason {

    public static void main(String args[]) {
        BagOfPrimitives obj = new BagOfPrimitives();  
        Gson gson = new Gson();  
        String json = gson.toJson(obj);    
        System.out.println(json); //==> json is {"value1":1,"value2":"abc"}  
          
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);   
        System.out.println(obj2.value1);  
        System.out.println(obj2.value2);  
        System.out.println(obj2.value3);//==>3  
          
        String json1 = "{'value1':1,'value2':'abc','value3':4}";  
        BagOfPrimitives obj3 = gson.fromJson(json1, BagOfPrimitives.class);   
        System.out.println(obj3.value1);  
        System.out.println(obj3.value2);  
        System.out.println(obj3.value3); //==>3  
    }
    
    static class BagOfPrimitives {  
        
        private int           value1    = 1;  
        private String        value2    = "abc";  
        //是用于声明变量在序列化的时候不被存储  
        private transient int   value3  = 3;  
      
        BagOfPrimitives() {  
            // no-args constructor  
        }  
     }
}
