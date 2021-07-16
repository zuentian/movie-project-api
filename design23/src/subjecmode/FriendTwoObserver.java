package subjecmode;

public class FriendTwoObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("FriendTwoObserver 知道了你发了动态了"+message);
    }
}
