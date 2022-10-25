package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private Connection dbConnection;
	
	public boolean connect() throws ClassNotFoundException {
		try {
			String url = "jdbc:sqlite:/mybank.db";
			Class.forName("org.sqlite.JDBC");
			this.dbConnection = DriverManager.getConnection(url);
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean disconnect() {
		try {
			if(!this.dbConnection.isClosed()) {
				this.dbConnection.close();
			}
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
}
