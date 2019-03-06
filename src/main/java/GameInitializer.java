import geometry.Vector;
import object.GameObjectFactory;

class GameInitializer {

    static void initialize(GameField gameField) {

        try {
            gameField.addObject(GameObjectFactory.create("tank", new Vector(1, 1), new Vector(2, 2)));
            gameField.addObject(GameObjectFactory.create("bullet", new Vector(4, 4), new Vector(5, 5)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
