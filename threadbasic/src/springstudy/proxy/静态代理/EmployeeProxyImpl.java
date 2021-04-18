package springstudy.proxy.静态代理;
//代理类EmployeeProxyImpl定义
public class EmployeeProxyImpl implements Employee {

    //代理类需要包含一个目标类的对象引用
    EmployeeImpl employee;

    public EmployeeProxyImpl (EmployeeImpl employee){
        this.employee = employee;
    }

    @Override
    public void calculateSalary(int id) {
        //在调用目标类的calculateSalary方法之前做的操作
        System.out.println("当前在正计算员工的工资开始");
        employee.calculateSalary(id);
        System.out.println("当前在正计算员工的工资结束");
    }
}
