package com.spscarstore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class DBconnection {
	public Connection getDBconnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/car","root","");
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
		return con;
	}

}
