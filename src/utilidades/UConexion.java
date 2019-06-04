package utilidades;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class UConexion {
	
	//String DBHelper = "jdbc:mysql://localhost:3306/javatest";
	private static UConexion instance = null;
	private Connection conn;
	
	private UConexion() {
		this.createConnection();
	}
	
	private void createConnection() {
		try {
			Properties properties = new Properties();
			properties.load(new FileReader("framework.properties"));
			Class.forName("com.mysql.jdbc.Driver");	
			String DBHelper = "jdbc:".concat(properties.getProperty("driver")).concat("://").concat(properties.getProperty("host")).concat(":").concat(properties.getProperty("port")).concat("/").concat(properties.getProperty("dbname"));
			this.conn = DriverManager.getConnection(DBHelper, properties.getProperty("user"), properties.getProperty("password", ""));
			System.out.println("Conecto");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	public static UConexion getInstance() {
		if (instance == null){
			instance = new UConexion();
		}
		return instance;
	}
	
	public Connection getConexion() {
		return this.conn;
	}

}
