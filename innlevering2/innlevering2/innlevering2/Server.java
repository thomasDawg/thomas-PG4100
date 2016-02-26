package innlevering2;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {

		new Thread(() -> {
			try {
				@SuppressWarnings("resource")
				ServerSocket serverSocket = new ServerSocket(8882);
				while (true) {
					Socket socket = serverSocket.accept();
					new Thread(new Quiz(socket)).start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

}
