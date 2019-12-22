import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadingThread implements Runnable {
    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("localhost", 8080);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line = bufferedReader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.err.println("Error due to reading information");
        }
    }
}
