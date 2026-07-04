package org.example;

import com.sun.tools.javac.Main;

public class ThreadLocalTest {
    /*
        ThreadLocal 相当于 一个线程的局部变量，每个线程的局部变量都是独立的，不会相互干扰。
        一个ThreadLocal只能存储一个数据，不能存储多个数据。
     */


    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
        local.set("Main Message");  // 设置线程局部变量的值

        System.out.println(Thread.currentThread().getName() + " : " + local.get()); // 获取线程名字和局部变量 （main ： Main Message）

        //创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 每个线程都会获取到自己的局部变量，不会相互干扰， 这里是新的线程， 所以不会取到上面的 Main Message
                System.out.println(Thread.currentThread().getName() + " : " + local.get());    // （Thread-0 ： null）
                //设置新的局部变量, 同理, 这里注入的值只在当前线程有效
                local.set("Sub Message");
                System.out.println(Thread.currentThread().getName() + " : " + local.get());    // （Thread-0 ： Sub Message）
            }
        }).start();

        local.remove(); // 删除局部变量

        System.out.println(Thread.currentThread().getName() + " : " + local.get());  //（main ： null）
    }
}
