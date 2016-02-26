package innlevering2;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainTest {

	private static ArrayList<Book> xL;
	public static void main(String[] args) throws SQLException {
		DbHandlerBokliste db = new  DbHandlerBokliste("root", "forreal");
		ArrayList<Book>list = (ArrayList<Book>) db.getTable();
	
		ArrayList<Book> x = new ArrayList<>();
		x.add(list.get(0));
		
		String navn = "NYGÅRDSHAUG, GERT";
		if(navn.equals(x.get(0).getAuthor())){
			System.out.println("AJKDSHDS");
		}else{
			System.out.println("qldkjaljsdl");
		}

	}	
	
	public void print(){
		
	}

}
