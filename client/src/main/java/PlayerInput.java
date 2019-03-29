import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class PlayerInput extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getKeyChar());
    }
}