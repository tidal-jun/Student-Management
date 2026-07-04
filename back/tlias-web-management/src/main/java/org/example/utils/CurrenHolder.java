package org.example.utils;

public class CurrenHolder {

    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setCurrentLocal(Integer id){
        CURRENT_LOCAL.set(id);
    }

    public static Integer getCurrentLocal(){
        return CURRENT_LOCAL.get();
    }

    public static void remove(){
        CURRENT_LOCAL.remove();
    }


   // private Integer Id;
   /*
    这里为什么不能使用静态变量来保存当前登录用户ID？
        是因为在多用户并发的Web系统中会导致严重的“数据串号”
        因为所有用户使用的变量数据都是这个静态变量，当多个用户同时访问这个静态变量的时候，就会产生数据串号。


        而使用ThreadLocal变量，每个用户访问的时候，都会为这个变量创建一个副本，
    */
}
