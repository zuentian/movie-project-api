package com.zuer.zuerlvdoubanmovie;

import com.zuer.zuerlvdoubanmovie.proxy.Person;
import com.zuer.zuerlvdoubanmovie.proxy.StuInvocationHandler;
import com.zuer.zuerlvdoubanmovie.proxy.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test1 {

    public static void main(String[] args) {

        //创建一个实例对象，这个对象是被代理的对象
        Person zhangsan = new Student();

        //创建一个与代理对象相关联的InvocationHandler
        InvocationHandler stuHandler = new StuInvocationHandler<Person>(zhangsan);

        //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);

        //代理执行上交班费的方法
        stuProxy.giveMoney();
    }
}
