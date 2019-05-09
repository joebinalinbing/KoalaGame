package koala;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class GameEvents extends Observable {

    int type;

    final int keyE = 1;

    final int collision = 2;

    int collisionDamage;

    Object event;

    public void setValue(KeyEvent e) {
        type = keyE;
        event = e;
        setChanged();
        notifyObservers(this);
    }

    public void setValue(String msg) {
        type = collision;
        event = msg;
        setChanged();
        notifyObservers(this);
    }

    public int getType() {
        return this.type;
    }

    public Object getEvent() {
        return this.event;
    }
}
