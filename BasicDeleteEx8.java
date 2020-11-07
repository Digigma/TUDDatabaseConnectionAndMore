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
public class BasicDeleteEx8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bn026";
		String user = "root";
		String password = "My5q1J3152@v@m";
		String sqlQuery = "SELECT * FROM lecturer";
		String sqlQuery1 = "SELECT * FROM lecturer WHERE subjectTaught NOT IN ('webDev', 'db', 'softDev3')";
		String sqlQuery2 = "SELECT * FROM lecturer";
		
		
		try(Connection conn = DriverManager.getConnection(url, user, password);
				Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE);) {
			//-----------------------------------------------------------------
			// introducing 3 more rows to the lecturer table 
			// if you run twice the program, it won't display S4 from Ex6
			//as it was deleted in this program and is not being reintroduced
			// to display it again, please run Exercise 7 first
			try (ResultSet result = st.executeQuery(sqlQuery)) {
				
				result.moveToInsertRow();
				
				result.updateString(1, "S5");
				result.updateInt(2, 23);
				result.updateString(3, "Rob");
				result.updateString(4, "lifeFailures");
				result.insertRow();
				
				result.updateString(1, "S6");
				result.updateInt(2, 23);
				result.updateString(3, "Bob");
				result.updateString(4, "lifeStyle");
				result.insertRow();
				
				result.updateString(1, "S7");
				result.updateInt(2, 23);
				result.updateString(3, "Robbie");
				result.updateString(4, "lifeIsLifeSong");
				result.insertRow();
				
				result.beforeFirst();
				// printing out the result
				ResultSetMetaData data = result.getMetaData();
				int colNum = data.getColumnCount();
				
				for(int i = 1; i <= colNum; i++) {
					System.out.printf("%20s", data.getColumnName(i));
				}
				
				System.out.println();
				// print out the result to see whether it has been updated
				while(result.next()) {
					for(int i = 1; i <=colNum; i++) {
						System.out.printf("%20s", result.getObject(i));
					}
					System.out.println();
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println();
			
			//-----------------------------------------------------------------
			// removing the rows that doesn't contain ('webDev', 'db', 'softDev3') keywords in the subjectTaught column
			try(ResultSet result1 = st.executeQuery(sqlQuery1)) {
				while(result1.next()) {
					result1.deleteRow();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println();
			
			//-----------------------------------------------------------------
			// printing out the final result
			try (ResultSet result2 = st.executeQuery(sqlQuery2)) {
	
				ResultSetMetaData data = result2.getMetaData();
				int colNum = data.getColumnCount();
				
				for(int i = 1; i <= colNum; i++) {
					System.out.printf("%20s", data.getColumnName(i));
				}
				
				System.out.println();

				while(result2.next()) {
					for(int i = 1; i <=colNum; i++) {
						System.out.printf("%20s", result2.getObject(i));
					}
					System.out.println();
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
			
	}

}
