package springstudy.proxy.静态代理;

public class EmployeeImpl implements Employee {
    @Override
    public void calculateSalary(int id) {
        System.out.println("计算员工工资");
    }
}
