package innlevering2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnectToDB;

public class DbHandlerBokliste {
	private ConnectToDB db;
	private Connection con;
	private PreparedStatement pstmtGetTable;

	public DbHandlerBokliste(String user, String password) {
		try {
			db = new ConnectToDB("localhost", "pg4100innlevering2", user, password);
			con = db.getConnection();
			pstmtGetTable = con.prepareStatement("SELECT * FROM bokliste");
		} catch (SQLException sqlEx) {
			System.err.println("ERROR: failed to connect with database..");
		}
	}

	public List<Book> getTable() {
		try {
			List<Book> list = new ArrayList<>();
			ResultSet res = pstmtGetTable.executeQuery();
			Book book = new Book();
			while (res.next()) {
				book = new Book(res.getString("forfatter"), res.getString("tittel"), res.getString("isbn"),
						res.getInt("sider"), res.getInt("utgitt"));
				list.add(book);
			}
			res.close();
			return list;
		} catch (SQLException sqlex) {
			System.err.println("ERROR: failed to retrive query from database");
		}
		return null;
	}

	public void close() throws SQLException {
		pstmtGetTable.close();
		con.close();
		db.close();
	}
}
