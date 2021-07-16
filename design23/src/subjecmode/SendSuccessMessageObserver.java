package subjecmode;

public class SendSuccessMessageObserver implements Observer {


    @Override
    public void update(String message) {
        System.out.println(System.currentTimeMillis()+"注册成功");
    }

    public static void main(String[] args) {
        ConcreteSubject subject = buildSubject();
        subject.notifyObservers("第1次");
        subject.notifyObservers("第2次");
        subject.notifyObservers("第3次");
        subject.notifyObservers("第4次");
    }

    private static ConcreteSubject buildSubject(){
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new SendSuccessMessageObserver());
        subject.attach(new SendNewPersonCouponObserver());
        return subject;
    }
}
