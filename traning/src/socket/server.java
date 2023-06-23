package socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class server {
	private List<ClientHandler> clients;

	public server() {
		clients = new ArrayList<>();
	}

	public void start(int port) {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server started on port " + port);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("New client connected: " + clientSocket);

				ClientHandler clientHandler = new ClientHandler(clientSocket);
				clients.add(clientHandler);
				clientHandler.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ClientHandler extends Thread {
		private Socket clientSocket;
		private BufferedReader inputReader;
		private PrintWriter outputWriter;

		public ClientHandler(Socket clientSocket) {
			try {
				this.clientSocket = clientSocket;
				inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				String message;
				while ((message = inputReader.readLine()) != null) {
					System.out.println("Received message: " + message);
					broadcastMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					inputReader.close();
					outputWriter.close();
					clientSocket.close();
					clients.remove(this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void broadcastMessage(String message) {
			for (ClientHandler client : clients) {
				if (client != this) {
					client.sendMessage(message);
				}
			}
		}

		private void sendMessage(String message) {
			outputWriter.println(message);
		}
	}

	public static void main(String[] args) {
		server chatServer = new server();
		chatServer.start(8888);
	}
}
