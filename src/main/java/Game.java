class Game extends Thread {

    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running = true;

    private GameField gameField;

    Game() {
        this.gameField = new GameField(new Point(0, 0), new Point(100, 100));

        try {
            gameField.addObject(new GameObject(new Point(10, 10), new Point(20, 20)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double lag = 0.0;
        double previous = getCurrentTime();

        while (isRunning()) {
            double current = getCurrentTime();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= MS_PER_UPDATE) {
                update();
                lag -= MS_PER_UPDATE;
            }
        }
    }

    boolean isRunning() {
        return running;
    }

    void terminate() throws InterruptedException {
        this.running = false;
        join();
    }

    private void update() {
        gameField.update();
    }

    private double getCurrentTime() {
        return System.currentTimeMillis();
    }
}
