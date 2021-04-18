package springstudy.proxy.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

//工厂类，定义一个通用的工具类用于实例化代理对象
//这个类可以适用于生成各种目标类的代理对象
public class ProxyInstanceFactory {

    public static Object createProxyInstance(Class clazz, InvocationHandler handler){
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),handler);
    }

}
