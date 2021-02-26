package JAVA高并发编程详解.chapter09;

public class ClassInit {
   /* private static int x=10;
    static {
        System.out.println(x);
        x=100;
    }*/

   static class Parent{
       static int value = 10;
       static {
           value = 20;
       }
       //子类使用父类的静态变量为自己的静态变量赋值
       static class Child extends Parent{
           static int i = value;
       }

       public static void main(String[] args) {
           System.out.println(Child.i);
       }
   }
}
