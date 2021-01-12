package 内置类和静态内置类;

public class Run {
    public static void main(String[] args) {
        PublicClass publicClass = new PublicClass();
        publicClass.setUsername("usernameValue");
        publicClass.setPassword("passwordValue");
        System.out.println(publicClass.getUsername()+" "+publicClass.getPassword());

        //实例化内置类
        PublicClass.PrivateClass privateClass = publicClass.new PrivateClass();

        privateClass.setAge("ageValue");
        privateClass.setAddress("addressValue");
        System.out.println(privateClass.getAge()+" "+privateClass.getAddress());


    }
}
