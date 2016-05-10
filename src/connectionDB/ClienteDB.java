package connectionDB;

import java.sql.Connection;
import java.sql.Statement;

import classes.Cliente;

/**
@author Diego Jacobs
Date: May 9, 2016
 */
public class ClienteDB {
	private Cliente cliente;
	private Connection con;
	
	public ClienteDB(Cliente cliente, myConnection con)
	{
		this.cliente = cliente;
		this.con = con.getCon();
	}
	
	/*
	 * Devolvemos 1 si se realizo con exito
	 * Devolvemos 0 si existio algun error
	 */
	public int selectCliente()
	{
		return 1;
	}
	
	/*
	 * Devolvemos 1 si se realizo con exito
	 * Devolvemos 0 si existio algun error
	 */
	public int insertCliente()
	{
		
		Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         
	         this.con.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = this.con.createStatement();
	         String sql = "INSERT INTO cliente (ID,NAME,AGE,ADDRESS,SALARY) "
	               + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
	         stmt.executeUpdate(sql);

	         stmt.close();
	         this.con.commit();
	         this.con.close();
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
		return 1;
	}
	
	/*
	 * Devolvemos 1 si se realizo con exito
	 * Devolvemos 0 si existio algun error
	 */
	public int updateCliente()
	{
		return 1;
	}
	
	/*
	 * Devolvemos 1 si se realizo con exito
	 * Devolvemos 0 si existio algun error
	 */
	public int deleteCliente()
	{
		return 1;
	}
	
}
