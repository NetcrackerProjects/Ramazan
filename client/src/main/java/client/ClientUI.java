package client;

import client.sprite.SpriteManager;

import javax.swing.JFrame;

class ClientUI extends JFrame {

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;

    ClientUI(SpriteManager spriteManager, PlayerInput playerInput) {
        add(new GamePanel(spriteManager, playerInput));

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        setTitle("client gui");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}