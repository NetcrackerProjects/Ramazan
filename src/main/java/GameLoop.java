public class GameLoop extends Thread{

    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running = true;

    @Override
    public void run(){

        double lag = 0.0;
        double previous = getCurrentTime();

        while(isRunning()){
            double current = getCurrentTime();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            processInput();

            while(lag >= MS_PER_UPDATE){
                update();
                lag -= MS_PER_UPDATE;
            }

        }

    }

    public boolean isRunning(){
        return running;
    }

    public void terminate() throws InterruptedException {
        running = false;
        join();
    }

    private void processInput(){

    }

    private void update(){

    }

    private double getCurrentTime(){
        return System.currentTimeMillis();
    }

}
