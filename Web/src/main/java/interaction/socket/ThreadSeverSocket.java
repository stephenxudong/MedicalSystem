package interaction.socket;

import interaction.threadPool.SocketPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadSeverSocket implements Runnable{

    private boolean running = true;
    private ServerSocket serverSocket;
    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadSeverSocket() {
        try {
            serverSocket = new ServerSocket(1235);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (running)
            running = false;
    }

    @Override
    public void run() {
        try {
            while(running) {
                Socket taskSocket = serverSocket.accept();
                threadPoolExecutor = new SocketPool().getSocketPool();
                threadPoolExecutor.execute(new Thread(new ThreadSocket(taskSocket)));
            }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        }
    }

}
