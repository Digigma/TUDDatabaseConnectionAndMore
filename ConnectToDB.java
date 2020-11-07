/**
 * 
 */
package week4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author digig
 *
 */
public class ConnectToDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url = "jdbc:mysql://localhost:3306/bn026";
		String user = "root";
		String password = "My5q1J3152@v@m";
		
		try(Connection conn = DriverManager.getConnection(url, user, password);
				Statement st = conn.createStatement();
				ResultSet result = st.executeQuery("SELECT * FROM lecturer")) {
			
			ResultSetMetaData data = result.getMetaData();
			int colNum = data.getColumnCount();
			
			for(int i = 1; i <= colNum; i++) {
				System.out.printf("%20s", data.getColumnName(i));
			}
			
			System.out.println();
			
			while(result.next()) {
				System.out.printf("%20s%20d%20s%20s\n",
						result.getString("staffID"),//you can get the result by the name of the column or...
						result.getInt("age"),
						result.getString(3), // you can get it by saying which number column do you want to have printed out
						result.getString(4));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}
}
