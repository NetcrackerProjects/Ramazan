package bonus;

import object.GameObject;

public interface Bonus {

    void applyBonus(GameObject gameObject);

    boolean canApplyBonus(GameObject gameObject);
}
