package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
	private Socket socket;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;

    public client(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            Thread receiveThread = new Thread(new MessageReceiver());
            receiveThread.start();

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = consoleReader.readLine()) != null) {
                outputWriter.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputReader.close();
                outputWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class MessageReceiver implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = inputReader.readLine()) != null) {
                    System.out.println("Received message: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
    	client chatClient = new client("localhost", 8888);
        chatClient.start();
    }
}