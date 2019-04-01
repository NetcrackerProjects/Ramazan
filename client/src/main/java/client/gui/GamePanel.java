package client.gui;

import client.control.PlayerInput;
import client.sprite.Sprite;
import client.sprite.SpriteManager;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GamePanel extends JPanel implements ActionListener {

    private static final int DELAY = 25;

    private final SpriteManager spriteManager;

    GamePanel(SpriteManager spriteManager, PlayerInput playerInput) {
        this.spriteManager = spriteManager;

        addKeyListener(playerInput);
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
