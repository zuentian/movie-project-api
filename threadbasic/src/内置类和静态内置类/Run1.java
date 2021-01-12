package 内置类和静态内置类;

public class Run1 {
    public static void main(String[] args) {
        PublicClassForStatic publicClass = new PublicClassForStatic();
        publicClass.setUsername("usernameValue");
        publicClass.setPassword("passwordValue");
        System.out.println(publicClass.getUsername()+" "+publicClass.getPassword());

        //实例化静态内置类
        PublicClassForStatic.PrivateClass privateClass = new PublicClassForStatic.PrivateClass();

        privateClass.setAge("ageValue");
        privateClass.setAddress("addressValue");
        System.out.println(privateClass.getAge()+" "+privateClass.getAddress());


    }
}
