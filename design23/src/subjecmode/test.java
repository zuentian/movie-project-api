package subjecmode;

public class test {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new FriendOneObserver());
        subject.attach(new FriendTwoObserver());

        subject.notifyObservers("第一个朋友圈消息");
    }
}
