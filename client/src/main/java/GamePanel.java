import sprite.Sprite;
import sprite.SpriteManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GamePanel extends JPanel implements ActionListener {

    private static final int DELAY = 25;

    private final SpriteManager spriteManager;

    GamePanel(SpriteManager spriteManager) {
        this.spriteManager = spriteManager;

        addKeyListener(new Input());
        setBackground(Color.black);
        setFocusable(true);

        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        draw(graphics);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    private void draw(Graphics graphics) {
        for (Sprite sprite : spriteManager.getSprites()) {
            graphics.drawImage(sprite.getImage(), (int) sprite.getX(), (int) sprite.getY(), this);
        }
    }
}
