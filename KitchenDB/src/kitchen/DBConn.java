package kitchen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConn {

	private static final String userName = "jharve1";
	private static final String password = "Cosc*tawf";
	private static final String serverName = "127.0.0.1";
	private static final int portNumber = 5030;
	private static final String dbName = "jharve1db";
	
	public static Connection getConnection() {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		try{
			conn = DriverManager.getConnection("jdbc:mysql://"
					+ serverName + ":" + portNumber + "/" + dbName,
					connectionProps);
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return conn;
	}
	
	public static ResultSet getResults(String query) throws SQLException{
		Connection conn = DBConn.getConnection();
		Statement qs = conn.createStatement();
		return qs.executeQuery(query);
	}

}