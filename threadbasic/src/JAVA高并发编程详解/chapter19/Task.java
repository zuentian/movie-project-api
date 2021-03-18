package JAVA高并发编程详解.chapter19;
@FunctionalInterface//“函数式接口”是指仅仅只包含一个抽象方法的接口
public interface Task<IN,OUT>{

    //给定一个参数，经过计算返回结果
    OUT get(IN input);

}
