package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectionDB.myConnection;

/**
@author Diego Jacobs
Date: May 7, 2016
 */
public class Contacto {
	private int id;
	private int telefono;
	private String email;
	private String twitter;
	private Connection con;
	private ArrayList<Contacto> all; //Solo se encuentra lleno si ejecutamos selectAllContactos

	public Contacto() {
		this.id = -1;
		this.telefono = -1;
		this.email = new String();
		this.twitter = new String();
		this.all = new ArrayList<Contacto>();
	}

	public Contacto(int telefono, String email, String twitter) {
		super();
		this.telefono = telefono;
		this.email = email;
		this.twitter = twitter;
		this.all = new ArrayList<Contacto>();
	}

	public Contacto(int telefono, String email, String twitter, myConnection con) {
		super();
		this.telefono = telefono;
		this.email = email;
		this.twitter = twitter;
		this.con = con.getCon();
		this.all = new ArrayList<Contacto>();
	}

	public Contacto(int id, int telefono, String email, String twitter) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.email = email;
		this.twitter = twitter;
		this.all = new ArrayList<Contacto>();
	}

	public Contacto(int id, int telefono, String email, String twitter, myConnection con) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.email = email;
		this.twitter = twitter;
		this.con = con.getCon();
		this.all = new ArrayList<Contacto>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTwitter() {
		return twitter;
	}
	
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	
	public ArrayList<Contacto> getAll() {
		return all;
	}

	public void setAll(ArrayList<Contacto> all) {
		this.all = all;
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	/*
	 * Devolvemos null si se realizo con exito
	 * Devolvemos el error si existio alguno
	 */
	public String selectAllContactos()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM contacto;" );
	        while ( rs.next() ) 
	        {
	           int Id = rs.getInt("id");
	           String  email = rs.getString("email");
	           int phone  = rs.getInt("telefono");
	           String  twitter = rs.getString("twitter");
	           
	           this.all.add(new Contacto(Id, phone, email, twitter));
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
	public String selectContacto()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	
	        String id = Integer.toString(this.getId());
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM contacto WHERE id = "+ id +";" );
	        while ( rs.next() ) 
	        {
	           int Id = rs.getInt("id");
	           String  email = rs.getString("email");
	           int phone  = rs.getInt("telefono");
	           String  twitter = rs.getString("twitter");
	           
	           this.setId(Id);
	           this.setEmail(email);
	           this.setTelefono(phone);
	           this.setTwitter(twitter);
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
	public String insertContacto()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery( "SELECT id FROM contacto WHERE id >= ALL (SELECT id FROM contacto);" );
		    
		    if (rs.next())
		    {
			    String id = Integer.toString(rs.getInt("id")+1);
				String email = this.getEmail();
				String phone = Integer.toString(this.getTelefono());
				String twitter = this.getTwitter();
				 
				String sql = "INSERT INTO contacto (id, telefono, email, twitter) " + "VALUES (" + id + "," + phone +", '" + email + "', '" + twitter + "');";
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
	public String updateContacto()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    String id = Integer.toString(this.getId());
		    String email = this.getEmail();
			String phone = Integer.toString(this.getTelefono());
			String twitter = this.getTwitter();
			 
			String sql = "UPDATE contacto SET telefono=" + phone + ", email='" + email + "', twitter='" + twitter +  "' WHERE id=" + id + ";";
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
	public String deleteContacto()
	{
       Statement stmt = null;
       try 
       {
    	   this.con.setAutoCommit(false);
	       System.out.println("Opened database successfully");

	       String id = Integer.toString(this.getId());
	       
	       stmt = this.con.createStatement();
	       String sql = "DELETE from contacto where id =" + id +";";
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
