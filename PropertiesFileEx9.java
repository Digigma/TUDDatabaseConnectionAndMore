/**
 * 
 */
package week4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author digig
 *
 */
public class PropertiesFileEx9 {
	
	static String url, user, password;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(".\\src\\week4\\bn026.properties"));
			
			Properties bn026prop = new Properties();
		
			bn026prop.load(reader);
		
			url = bn026prop.getProperty("url");
			user = bn026prop.getProperty("user");
			password = bn026prop.getProperty("password");
		
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(url, user, password)) {
			System.out.println("Connected successfully!");
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
