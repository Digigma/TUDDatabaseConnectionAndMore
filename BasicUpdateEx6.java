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
public class BasicUpdateEx6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bn026";
		String user = "root";
		String password = "YourPassword";
		String sqlQuery = "SELECT * FROM lecturer WHERE lecturerName LIKE \'%a'";
		
		try(Connection conn = DriverManager.getConnection(url, user, password);
				Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE);
				ResultSet result = st.executeQuery(sqlQuery)) {
			
			result.absolute(1); // set cursor at row 1
			do {
				result.updateString(2, "19");
				result.updateRow();
			}while(result.next());
			result.beforeFirst(); // reset cursor to just before first row
			
			ResultSetMetaData data = result.getMetaData();
			int colNum = data.getColumnCount();
			
			for(int i = 1; i <= colNum; i++) {
				System.out.printf("%20s", data.getColumnName(i));
			}
			
			System.out.println();
			
			// print out the result to see whether it has been updated
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
