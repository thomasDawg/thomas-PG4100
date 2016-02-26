package innlevering2;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuizMaker {

	private ArrayList<Book> books;
	private int randomInt;

	public QuizMaker() {
		try {
			getQuizStuffFromDB();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	public String makeQuestions() {
		randomInt = (int) (Math.random() * 7) + 1;
		String titleQuestion = books.get(randomInt).getTitle();
		return "Hvem har skrevet " + titleQuestion + "?\n";
	}

	public String makeAnswers() {
		String authorAnswer = books.get(randomInt).getAuthor();
		return authorAnswer;
	}

	public void getQuizStuffFromDB() throws SQLException {
		DbHandlerBokliste db = new DbHandlerBokliste("root", "forreal");
		books = (ArrayList<Book>) db.getTable();
		db.close();
	}
}
