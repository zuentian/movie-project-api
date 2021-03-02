package JAVA高并发编程详解.chapter14;

/**
 * 枚举类+静态内部类方法
 */
public class SingletonEnumNew {

    private SingletonEnumNew(){

    }

    private enum EnumHolder{
        INSTANCE;
        private SingletonEnumNew instance;

        EnumHolder(){
            this.instance = new SingletonEnumNew();
        }
        private SingletonEnumNew getSingleton(){
            return instance;
        }
    }

    public static SingletonEnumNew getInstance(){
        return EnumHolder.INSTANCE.getSingleton();
    }
}
