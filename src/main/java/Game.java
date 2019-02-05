public class Game extends Thread{

    private final static double interval = 0.1;
    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running = true;

    private GameField gameField;

    public Game(){
        init();
    }

    @Override
    public void run(){
        double lag = 0.0;
        double previous = getCurrentTime();

        while(isRunning()){
            double current = getCurrentTime();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while(lag >= MS_PER_UPDATE){
                update(interval);
                lag -= MS_PER_UPDATE;
            }
        }
    }

    public boolean isRunning(){
        return running;
    }

    public void terminate() throws InterruptedException {
        this.running = false;
        join();
    }

    private void init(){
        this.gameField = new GameField(new Point(100, 100));
        try {
            gameField.addObject(new GameObject(new Point(10, 10), new Point(10, 10)));
        } catch (OutOfBoundaryException | ObjectIntersectionException e) {
            e.printStackTrace();
        }
    }

    private void update(double delta){
        gameField.update(delta);
    }

    private double getCurrentTime(){
        return System.currentTimeMillis();
    }
}
