import action.ActionManager;
import geometry.Rectangle;
import interaction.InteractionProcessor;
import interaction.InteractionRuleBase;
import interaction.InteractionType;
import interaction.rule.BonusTankInteractionRule;
import interaction.rule.TankBulletInteractionRule;
import object.BonusHolder;
import object.Bullet;
import object.Tank;
import object.Type;
import object.manager.ObjectManager;
import physic.PhysicManager;

class GameFacade {

    private ActionManager actionManager;
    private PhysicManager physicManager;
    private InteractionProcessor interactionProcessor;

    void setupGameElements(Rectangle fieldRectangle) {
        this.actionManager = new ActionManager();
        this.physicManager = new PhysicManager(fieldRectangle);

        GameObjectInitializer gameObjectInitializer = new GameObjectInitializer();

        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<BonusHolder> bonusHolderObjectManager = new ObjectManager<>(physicManager);

        gameObjectInitializer.createTanks(tankObjectManager);
        gameObjectInitializer.createBullets(bulletObjectManager);

        InteractionRuleBase interactionRuleBase = new InteractionRuleBase();

        interactionRuleBase.addRule(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(tankObjectManager, bulletObjectManager));
        interactionRuleBase.addRule(new InteractionType(Type.BONUS, Type.TANK),
                new BonusTankInteractionRule(bonusHolderObjectManager, tankObjectManager));

        this.interactionProcessor = new InteractionProcessor(interactionRuleBase);
    }


    ActionManager getActionManager() {
        return actionManager;
    }

    PhysicManager getPhysicManager() {
        return physicManager;
    }

    InteractionProcessor getInteractionProcess() {
        return interactionProcessor;
    }
}
