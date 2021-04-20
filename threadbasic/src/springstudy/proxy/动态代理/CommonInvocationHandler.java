package springstudy.proxy.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CommonInvocationHandler implements InvocationHandler {

    Object targetObj ;
    public Object bind(Object targetObj){
        this.targetObj = targetObj;
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(),
                targetObj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(1111);
        method.invoke(targetObj,args);
        System.out.println(2222);
        return null;
    }
}
