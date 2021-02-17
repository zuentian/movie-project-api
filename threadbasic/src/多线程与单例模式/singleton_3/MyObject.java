package 多线程与单例模式.singleton_3;

/**
 * 此方法使同步synchronized语句块，只对实例化对象的关键代码进行同步
 * 从语句的结构上来讲，运行效率的确得到了提升，但是如果遇到多线程的情况下
 * 还是无法解决得到同一个实例对象的结果
 */
public class MyObject {
    private static MyObject myObject;
    private MyObject(){

    }
    public static MyObject getInstance(){
        try {
            if(myObject != null){

            }else {
                Thread.sleep(3000);
                synchronized (MyObject.class){
                    myObject = new MyObject();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObject;
    }
}
