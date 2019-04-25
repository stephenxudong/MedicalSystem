package ThreadPool;

import java.util.concurrent.*;

public class SocketPool {

    private static ThreadPoolExecutor socketPool;
    private static ThreadPoolExecutor requestHandlerPool;

    public SocketPool(){
        //建立一个阻塞队列，建立线程池
        BlockingQueue<Runnable> blockSocket = new LinkedBlockingQueue<Runnable>(100);
        BlockingQueue<Runnable> blockRequest = new LinkedBlockingQueue<Runnable>(100);

        socketPool = new ThreadPoolExecutor(50, 300, 1, TimeUnit.DAYS, blockSocket);
        requestHandlerPool = new ThreadPoolExecutor(50, 300, 1, TimeUnit.MINUTES, blockRequest);
    }

    public ThreadPoolExecutor getSocketPool() {
        return socketPool;
    }

    public ThreadPoolExecutor getRequestHandlerPool() {
        return requestHandlerPool;
    }
}
