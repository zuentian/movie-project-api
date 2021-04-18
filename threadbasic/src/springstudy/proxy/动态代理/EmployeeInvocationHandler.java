package springstudy.proxy.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
//每个目标类实现一个handler子类
public class EmployeeInvocationHandler implements InvocationHandler {

    EmployeeImpl employee;

    public EmployeeInvocationHandler(EmployeeImpl employee){
        this.employee = employee;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("计算员工工资开始");
        method.invoke(employee,args);
        System.out.println("计算员工工资结束");
        return null;
    }
}
