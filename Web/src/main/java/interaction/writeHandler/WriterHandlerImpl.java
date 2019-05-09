package interaction.writeHandler;

import interaction.infolocker.AESEncode;

import java.io.BufferedWriter;
import java.io.IOException;

public class WriterHandlerImpl implements WriteHandler {
    private BufferedWriter writer;

    public WriterHandlerImpl(BufferedWriter writer)
    {
        this.writer = writer;
    }

    @Override
    public void write(String context){
        int chunk = context.length()/1024;
        synchronized (writer)
        {
            try {
                for (int i = 0; i < chunk; i++) {
                    int index = i * 1024;
                    writer.write(context.substring(index, index + 1024));
                    writer.flush();
                }
                writer.write(context.substring(chunk * 1024, context.length()));
                writer.flush();
            }
            catch(IOException e)
            {}
        }
    }
}
