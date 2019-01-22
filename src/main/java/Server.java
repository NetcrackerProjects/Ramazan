import java.io.IOException;
import java.net.*;

public class Server extends Thread{

    private ServerSocket serverSocket;
    private volatile boolean running = true;

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(isRunning()){
            try {
                new ClientHandler(serverSocket.accept()).start();
            } catch (SocketException e){
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void terminate() throws IOException, InterruptedException {
        running = false;
        serverSocket.close();
        join();
    }
}
