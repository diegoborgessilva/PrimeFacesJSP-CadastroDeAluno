package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBase {
	public static final String URL="jdbc:mysql://localhost/lpiii_prova3";
	public static final String USUARIO="root";
	public static final String SENHA="root";
	
	public static Connection getConnection() throws SQLException {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return DriverManager.getConnection(URL, USUARIO, SENHA);
		
	}

}