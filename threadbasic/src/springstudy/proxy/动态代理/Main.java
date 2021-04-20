package springstudy.proxy.动态代理;

public class Main {

    public static void main(String[] args) {
        //创建被代理的对象
        //EmployeeImpl employee = new EmployeeImpl();
        Employee employee = new EmployeeImpl();

        /*EmployeeInvocationHandler handler = new EmployeeInvocationHandler(employee);

        Employee proxyInstance = (Employee) ProxyInstanceFactory.createProxyInstance(
                EmployeeImpl.class,handler);

        proxyInstance.calculateSalary(1);*/

        CommonInvocationHandler handler = new CommonInvocationHandler();
        Employee proxyInstance = (Employee) handler.bind(employee);
        proxyInstance.calculateSalary(1);

    }
}
