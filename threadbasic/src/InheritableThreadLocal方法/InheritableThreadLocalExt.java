package InheritableThreadLocal方法;

import java.util.Date;

public class InheritableThreadLocalExt extends InheritableThreadLocal {
    @Override
    protected Object initialValue() {
        return System.currentTimeMillis();
    }

    /**
     * 如果在继承的同事还可以对值进行进一步的处理
     * @param parentValue
     * @return
     */
    @Override
    protected Object childValue(Object parentValue) {
        return parentValue + " 我在子线程加的~！";
    }
}
