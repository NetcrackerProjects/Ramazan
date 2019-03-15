package engine.object;

public class TokenManager {

    private int currentId;

    public TokenManager() {
        this.currentId = 0;
    }

    public int getNewId() {
        return currentId++;
    }
}
