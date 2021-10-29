package Prestige_Banking;

import Prestige_Banking.domain.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnect() {
		Connection conn=null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //For oracle DB
		
			System.out.println("Driver loaded successfully .....");
			//sample URL :  http://google.com:80  (80 is default portno)
			String url = "jdbc:oracle:thin:@localhost:1521:ORCL"; //for oracle 
		
			String uname = "scott";
			String pwd = "tiger";
			
			conn = DriverManager.getConnection(url,uname,pwd);
			System.out.println("Connected successfully with the DB .....");
			System.out.println();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

