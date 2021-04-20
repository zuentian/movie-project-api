package clone;


import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Student student = new Student();
        Student student1 = new Student();
        student.setId("1");
        student.setName("2222");

        student1 = student;
        student1.setName("44555");

        System.out.println(student);
        System.out.println(student1);


        //BeanUtils.copyProperties(student1, student);

        System.out.println(student);
        System.out.println(student1);

    }
}
