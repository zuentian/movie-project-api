package subjecmode;

public class FriendOneObserver implements Observer{


    @Override
    public void update(String message) {
        System.out.println("FriendOneObserver 知道了你发了动态了"+message);
    }
}
