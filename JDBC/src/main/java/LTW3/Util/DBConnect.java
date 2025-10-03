package LTW3.Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static Connection getConnection() throws Exception {
	    String serverName = "MINH_NHUT\\MINH_NHUT"; 
	    String dbName = "kt";
	    String portNumber = "1433";
	    String instance = ""; 
	    String userID = "sa";
	    String password = "123456";

	    String url;
	    if (instance == null || instance.trim().isEmpty()) {
	        url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName
	                + ";encrypt=false;trustServerCertificate=true";
	    } else {
	        url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber + ";databaseName=" + dbName
	                + ";encrypt=false;trustServerCertificate=true";
	    }

	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    return DriverManager.getConnection(url, userID, password);
	}

	public static void main(String[] args) {
		try (Connection conn =  DBConnect.getConnection()) {
			System.out.println("Connected: " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
