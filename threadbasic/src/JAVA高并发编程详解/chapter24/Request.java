package JAVA高并发编程详解.chapter24;

/**
 * 客户提交的任何业务受理请求都会被封装成Request对象
 */
public class Request {
    private final String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return business;
    }
}
