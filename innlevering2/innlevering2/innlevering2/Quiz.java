package innlevering2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Quiz implements Runnable {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String clientMessage;
	private QuizMaker quizMaker = new QuizMaker();
	private boolean clientPlay = false;

	public Quiz(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			setupStreams();
			initQuiz();
			while (true) {
				while (!clientPlay) {
					String yesOrNo = clientMessage();
					continueQuiz(yesOrNo);
				}
				startQuizRound();
			}
		} catch (Exception e) {
			System.exit(0);
			System.err.println("ERROR: Someting went wrong..");
		}
	}

	private void setupStreams() {
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initQuiz() {
		try {
			sendMessage("**********************************\n* Velkommen til forfatter-Quiz  *\n"
					+ "**********************************\n\n");
			sendMessage("Vil du delta i forfatter-Quiz? (ja/nei)\n");
		} catch (Exception e) {
			System.err.println("ERROR: client terminated the connection");
		}

	}

	private void startQuizRound() {
		String answerFromClient;
		String question;
		String answer;
		try {
			question = quizMaker.makeQuestions();
			answer = quizMaker.makeAnswers();
			sendMessage(question);
			answerFromClient = clientMessage();
			answerChecker(answer, answerFromClient);
			sendMessage("Vil du fortsette? (ja/nei)\n");
			clientPlay = false;
		} catch (Exception e) {
		}
	}

	private void answerChecker(String ans, String client) {
		String lastName = ans.substring(0, ans.indexOf(","));
		String firstName = ans.substring(lastName.length() + 2, ans.length());

		if (client.equals(lastName) || client.equals(firstName + " " + lastName) || client.equals(ans)
				|| client.equals(lastName + " " + firstName)) {
			sendMessage("Riktig!" + "\n");
		} else {
			sendMessage("Feil! det var: " + firstName + " " + lastName + "\n");
		}
	}

	private void continueQuiz(String yesOrNo) {
		try {
			if (yesOrNo.equalsIgnoreCase("ja")) {
				clientPlay = true;
			} else if (yesOrNo.equalsIgnoreCase("nei")) {
				endQuiz();
			} else {
				sendMessage("ERROR: skriv inn ja eller nei\n");
			}
		} catch (Exception e) {
		}

	}

	private void endQuiz() {
		sendMessage("Takk for at du deltok!\n");
		try {
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
		}
	}

	public void sendMessage(String msg) {
		try {
			output.writeObject(msg);
			output.flush();
		} catch (IOException e) {
		}
	}

	public String clientMessage() {
		try {
			clientMessage = (String) input.readObject();
		} catch (ClassNotFoundException | IOException e) {
		}
		return clientMessage;
	}

}
