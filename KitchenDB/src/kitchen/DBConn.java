package kitchen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConn {

	private static String userName = "jharve1";
	private static String password = "Cosc*tawf";
	private static String serverName = "127.0.0.1";
	private static String portNumber = "5030";
	private static String dbName = "jharve1db";
	
	public static void setUserName(String x){
		userName = x;
	}
	
	public static void setPassword(String x){
		password = x;
	}
	
	public static void setServerName(String x){
		serverName = x;
	}
	
	public static void setPortNumber(String x){
		portNumber = x;
	}
	
	public static void setDBName(String x){
		dbName = x;
	}
	
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
	
	public static boolean updateDB(String query) throws SQLException{
		Connection conn = DBConn.getConnection();
		Statement qs = conn.createStatement();
		return qs.execute(query);
	}

}