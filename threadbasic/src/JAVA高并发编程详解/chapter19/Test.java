package JAVA高并发编程详解.chapter19;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        List<Teacher> list = new ArrayList<Teacher>();
        for (int i = 0; i < 10 ; i++) {
            Teacher teacher = new Teacher();
            teacher.setId(i+"");
            teacher.setName("张三");
            list.add(teacher);
        }
        for(Teacher t: list){
            System.out.println(t);
            System.out.println(t.getFullName());
        }

    }
}

class Teacher{

    private String id;
    private String name;
    private String fullName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName(){
        return this.id+"-"+this.name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
