package springstudy.proxy.静态代理;

/**
 * 代理类的信息是在jvm运行之前就已经生成好了，逻辑由开发者实现
 * 代理类与目标类的定义应该严格参照规范，定义公共接口并实现它，需要代理的方法在接口中都要定义好
 */
public class Main {
    public static void main(String[] args) {
        //实例化一个目标类对象
        EmployeeImpl employee = new EmployeeImpl();
        //将目标类对象传给代理类的构造方法，因为代理类中含有一个目标类的对象引用
        //从而指定这个代理类对象代理的是哪个目标类对象实例
        EmployeeProxyImpl employeeProxy = new EmployeeProxyImpl(employee);
        employeeProxy.calculateSalary(1);

    }
}
