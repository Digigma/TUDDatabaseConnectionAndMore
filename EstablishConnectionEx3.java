/**
 * 
 */
package week4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author digig
 *
 */
public class EstablishConnectionEx3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bn026";
		String user = "root";
		String password = "My5q1J3152@v@m";
		
		try(Connection conn = DriverManager.getConnection(url, user, password)) {
			System.out.println("Connected successfully!");
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
