package koala;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyControl extends KeyAdapter {

    private GameEvents gameEvents;

    public KeyControl() {
    }

    public KeyControl(GameEvents ge) {
        this.gameEvents = ge;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameEvents.setValue(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameEvents.setValue(e);
    }
}
