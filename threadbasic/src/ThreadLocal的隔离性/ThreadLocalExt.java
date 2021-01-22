package ThreadLocal的隔离性;

import java.util.Date;

/**
 * 覆盖initialValue()方法具有初始值
 */
public class ThreadLocalExt extends ThreadLocal {

    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }
}
