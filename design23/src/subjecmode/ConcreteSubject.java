package subjecmode;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject{

    //订阅者容器
    private List<Observer> observers = new ArrayList<Observer>();
    @Override
    public void attach(Observer observer) {

        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer observer : observers){
            observer.update(message);
        }
    }
}
