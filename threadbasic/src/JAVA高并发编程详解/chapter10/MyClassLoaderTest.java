package JAVA高并发编程详解.chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //声明一个MyClassLoader
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> aClass = classLoader.loadClass
                ("JAVA高并发编程详解.chapter10.HelloWorld");

        System.out.println(aClass.getClassLoader());

        Object helloWorld = aClass.newInstance();
        System.out.println(helloWorld);
        Method welcomeMethod = aClass.getMethod("welcome");

        String result = (String) welcomeMethod.invoke(helloWorld);

        System.out.println("Result:"+result);
    }
}
