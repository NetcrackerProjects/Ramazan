package action;

import object.Deletable;

public class DeleteObjectAction implements Action {

    private final Deletable deletable;

    public DeleteObjectAction(Deletable deletable) {
        this.deletable = deletable;
    }

    @Override
    public void doAction() {
        deletable.setOnDelete();
    }
}
