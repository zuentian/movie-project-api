package subjecmode;

/**
 * 主题，主要由类实现的可观察接口
 */
public interface Subject {
    //添加订阅关系
    void attach (Observer observer);

    //移除订阅关系
    void detach (Observer observer);

    //通知订阅者
    void notifyObservers(String message);
}
