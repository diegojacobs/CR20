package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
@author Diego Jacobs
Date: May 7, 2016
 */
public class Estado {
	private int id;
	private String descripcion;
	private Connection con;
	private ArrayList<Estado> all;
	
	public Estado() {
		this.id = -1;
		this.descripcion = new String();
		this.all = new ArrayList<Estado>();
	}
	
	public Estado(String descripcion) {
		super();
		this.descripcion = descripcion;
		this.all = new ArrayList<Estado>();
	}

	public Estado(String descripcion, Connection con) {
		super();
		this.descripcion = descripcion;
		this.con = con;
		this.all = new ArrayList<Estado>();
	}

	public Estado(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.all = new ArrayList<Estado>();
	}

	public Estado(int id, String descripcion, Connection con) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.con = con;
		this.all = new ArrayList<Estado>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public ArrayList<Estado> getAll() {
		return all;
	}

	public void setAll(ArrayList<Estado> all) {
		this.all = all;
	}

	/*
	 * Devolvemos null si se realizo con exito
	 * Devolvemos el error si existio alguno
	 */
	public String selectAllEstados()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM estado;" );
	        while ( rs.next() ) 
	        {
	           int Id = rs.getInt("id");
	           String  des = rs.getString("descripcion");
	           
	           this.all.add(new Estado(Id, des));
	        }
	        rs.close();
	        stmt.close();
	        this.con.close();
		} 
		catch ( Exception e ) 
		{
			return e.getClass().getName()+": "+ e.getMessage();
		}
		
	    return null;
    }
	
	/*
	 * Devolvemos null si se realizo con exito
	 * Devolvemos el error si existio alguno
	 */
	public String selectEstados()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	
	        String id = Integer.toString(this.getId());
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM estado WHERE id = "+ id +";" );
	        while ( rs.next() ) 
	        {
	        	int Id = rs.getInt("id");
		        String  des = rs.getString("descripcion");
	           
	           this.setId(Id);
	           this.setDescripcion(des);
	           System.out.println();
	        }
	        rs.close();
	        stmt.close();
	        this.con.close();
		} 
		catch ( Exception e ) 
		{
			return e.getClass().getName()+": "+ e.getMessage();
		}
		
	    return null;
    }
	
	/*
	 * Devolvemos null si se realizo con exito
	 * Devolvemos el error si existio alguno
	 */
	public String insertEstados()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery( "SELECT id FROM estado WHERE id >= ALL (SELECT id FROM estado);" );
		    
		    if (rs.next())
		    {
			    String id = Integer.toString(rs.getInt("id")+1);
			    String des = this.getDescripcion();
			 
				String sql = "INSERT INTO estado (id, descripcion) " + "VALUES (" + id + ", '" + des +"' );";
			    stmt.executeUpdate(sql);
		    }
		    
		    stmt.close();
		    this.con.commit();
		    this.con.close();
		} 
		catch (Exception e) 
		{
			return e.getClass().getName()+": "+ e.getMessage();
		}
		
	    return null;
	}
	
	/*
	 * Devolvemos null si se realizo con exito
	 * Devolvemos el error si existio alguno
	 */
	public String updateEstados()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    String id = Integer.toString(this.getId());
		    String des = this.getDescripcion();
			 
			String sql = "UPDATE estado SET descripcion='" + des + "' WHERE id=" + id + ";";
			stmt.executeUpdate(sql);
		
			stmt.close();
			this.con.commit();
			this.con.close();
		} 
		catch (Exception e) 
		{
			return e.getClass().getName()+": "+ e.getMessage();
		}
		
	    return null;
	}
	
	/*
	 * Devolvemos null si se realizo con exito
	 * Devolvemos el error si existio alguno
	 */
	public String deleteEstados()
	{
       Statement stmt = null;
       try 
       {
    	   this.con.setAutoCommit(false);
	       System.out.println("Opened database successfully");

	       String id = Integer.toString(this.getId());
	       
	       stmt = this.con.createStatement();
	       String sql = "DELETE from estado where id =" + id +";";
	       stmt.executeUpdate(sql);
	       this.con.commit();

			stmt.close();
			this.con.close();
       } 
       catch ( Exception e ) 
       {
         return e.getClass().getName()+": "+ e.getMessage();
       }
       return null;
	}
}
