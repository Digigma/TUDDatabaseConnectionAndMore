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
public class UsingMetaDataEx5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bn026";
		String user = "root";
		String password = "YourPassword";
		String sqlQuery = "SELECT * FROM lecturer";
		
		try(Connection conn = DriverManager.getConnection(url, user, password);
				Statement st = conn.createStatement();
				ResultSet result = st.executeQuery(sqlQuery)) {
			
			ResultSetMetaData data = result.getMetaData();
			int colNum = data.getColumnCount();
			
			for(int i = 1; i <= colNum; i++) {
				System.out.printf("%20s", data.getColumnName(i));
			}
			
			System.out.println();
			
			while(result.next()) {
				for(int i = 1; i <= colNum; i++) 
					System.out.printf("%20s", result.getObject(i));
				System.out.println();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
