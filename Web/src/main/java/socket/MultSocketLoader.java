package socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static java.lang.System.exit;

public class MultSocketLoader implements ServletContextListener{

    private ThreadSeverSocket threadSeverSocket;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        if(null != threadSeverSocket) {
            threadSeverSocket.stop();
            exit(0);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        threadSeverSocket = new ThreadSeverSocket();
        new Thread(threadSeverSocket).start();
    }
}
