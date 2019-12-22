import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends JFrame implements Runnable {
    JTextArea output = new JTextArea();
    JTextArea input = new JTextArea();
    JButton sendButton = new JButton("Send");
    private Socket clientSocket;

    Client() {
        setVisible(true);
        setBounds(400, 300, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        output.setEditable(false);
        add(output, BorderLayout.CENTER);
        add(sendButton, BorderLayout.PAGE_END);
        add(input, BorderLayout.PAGE_START);
        sendButton.addActionListener(event -> {
            sendInfoOnServer(input.getText());
            input.setText("");
            new Thread(this).start();
        });

    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(Client::new);
    }

    private void sendInfoOnServer(String info) {
        try {
            clientSocket = new Socket("localhost", 8080);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
            printWriter.println(info);
            printWriter.flush();
        } catch (IOException e) {
            System.err.println("error during sending...");
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            do {
                output.append(bufferedReader.readLine());
            } while (bufferedReader.readLine() != null);
        } catch (IOException e) {
            System.err.println("error during reading info");
        }
    }
}
