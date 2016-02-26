package innlevering2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea quizWindow;
	private JTextField clientTxt;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket clientSocket;
	private String host;
	private String message;
	private int port = 8882;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client("localhost");
		client.startClient();
	}

	public Client(String host) {
		super("client");
		this.host = host;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientTxt = new JTextField();
		clientTxt.setEditable(true);
		clientTxt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(e.getActionCommand());
				clientTxt.setText("");
			}
		});
		add(clientTxt, BorderLayout.NORTH);
		quizWindow = new JTextArea();
		quizWindow.setEditable(false);
		add(new JScrollPane(quizWindow), BorderLayout.CENTER);
		setSize(400, 200);
		setVisible(true);
	}

	private void startClient() {
		try {
			clientSocket = new Socket(host, port);
			setupStreams();
			playingQuiz();
		} catch (EOFException eof) {
			showMessage("\n Client terminated the connection..");
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			close();
		}
	}

	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(clientSocket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(clientSocket.getInputStream());
	}

	private void playingQuiz() {
		try {
			do {
				message = (String) input.readObject();
				showMessage(message);
			} while (!message.equals("Takk for at du deltok!"));
		} catch (Exception e) {
		}
		clientTxt.setEditable(false);
	}

	public void showMessage(final String m) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				quizWindow.append(m);
			}
		});
	}

	public void sendMessage(String message) {
		try {
			String msgOut = message.toLowerCase();
			output.writeObject(msgOut);
			output.flush();
			showMessage(msgOut + "\n");
		} catch (IOException e) {
			quizWindow.append("\n something went wrong sending message to host");
		}
	}

	private void close() {
		try {
			input.close();
			output.close();
			clientSocket.close();
		} catch (IOException e) {
		}
	}
}
