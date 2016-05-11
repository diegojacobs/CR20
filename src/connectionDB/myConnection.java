package connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
@author Diego Jacobs
Date: May 9, 2016
 */
public class myConnection {
	private Connection con;
	
	public myConnection(String user, String password)
	{
		//Connection to DataBase
		this.con = null;
	    try
	    {
	      Class.forName("org.postgresql.Driver");
	      String url = "jdbc:postgresql://localhost:5432/baron_rojo";
	      this.con = DriverManager.getConnection(url, user, password);
	      System.out.println("You made it, take control your database now!");
	    }
	    catch (ClassNotFoundException e)
	    {
	      e.printStackTrace();
	      System.exit(1);
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	      System.exit(2);
	    }
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
