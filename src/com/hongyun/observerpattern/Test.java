
package com.hongyun.observerpattern;

import java.util.Observable;
import java.util.Observer;

public class Test {

    // ~ 常量区块
    // =========================================================================

    // ~ 成员变量区块
    // =========================================================================

    // ~ 构造函数区块
    // =========================================================================

    // ~ 方法区块
    // =========================================================================

    // ~ 静态方法区块
    // =========================================================================

    // ~ 内部接口（类）区块
    // =========================================================================
    public static void main(String[] args) {
        MyObject obj = new MyObject();
        MyObserver observer = new MyObserver();
        obj.addObserver(observer);
        obj.change();
        while(true) {
            try {
                obj.hasChanged();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class  MyObserver implements Observer{
        @Override
        public void update(Observable arg0, Object arg1) {
            System.out.println("yeah i am noticed by:");
        }
    }
    static class  MyObject extends Observable{
        
        /**
         * 发生改变，将通知观察者
         */
        public void change() {
            setChanged();
            notifyObservers();//这里一定要通知，不然观察者observer接收不到通知
        }
    }
}
