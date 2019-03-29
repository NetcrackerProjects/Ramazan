import engine.geometry.Vector;
import engine.object.TokenManager;
import game.object.GameObjectFactory;
import game.object.tank.Tank;
import sprite.SpriteFactory;
import sprite.SpriteManager;

import javax.swing.JFrame;
import java.awt.EventQueue;

class ClientUI extends JFrame {

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;

    private ClientUI(SpriteManager spriteManager) {
        add(new GamePanel(spriteManager));

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        setTitle("client gui");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        SpriteManager spriteManager = new SpriteManager();

        EventQueue.invokeLater(() -> {
            ClientUI clientUI = new ClientUI(spriteManager);
            clientUI.setVisible(true);
        });

        GameObjectFactory gameObjectFactory = new GameObjectFactory(new TokenManager());

        Tank tank = gameObjectFactory.createTank(new Vector(0, 0), new Vector(50, 50));

        SpriteFactory spriteFactory = new SpriteFactory();

        spriteManager.addSprite(spriteFactory.createSprite(tank));
    }
}