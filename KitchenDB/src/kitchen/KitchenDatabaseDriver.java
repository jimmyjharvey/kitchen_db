package kitchen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class KitchenDatabaseDriver {
	
	public static void main(String[] args)throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		Connection conn = DBConn.getConnection();
		
		Statement qs = conn.createStatement();
		ResultSet result = qs.executeQuery("SELECT * FROM COOK");
		
		while (result.next()) System.out.println(result.getString("CookName"));

	}

}
