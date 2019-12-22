import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                System.out.println("Server is ready for connect");
                Socket accept = serverSocket.accept();
                System.out.println("New Client Connected");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                String line = bufferedReader.readLine();
                System.out.println("get info: " + line);
                if ("ls".equals(line)) {
                    File file = new File("/home/irina/");
                    if (file.isDirectory()) {
                        System.out.println("Showing directory files...");
                        File[] list = file.listFiles();
                        PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
                        printWriter.println(Arrays.toString(list));
                        printWriter.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
