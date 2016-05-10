package connectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import classes.Contacto;

/**
@author Diego Jacobs
Date: May 9, 2016
 */
public class ContactoDB {
	private Contacto contact;
	private Connection con;
	
	public ContactoDB(Contacto contact, myConnection con)
	{
		this.contact = contact;
		this.con = con.getCon();
	}
	
	/*
	 * Devolvemos 1 si se realizo con exito
	 * Devolvemos 0 si existio algun error
	 */
	public void selectAllContactos()
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
	           
	           System.out.println( "ID = " + Id );
	           System.out.println( "Email = " + email );
	           System.out.println( "Telefono = " + phone );
	           System.out.println( "twitter = @" + twitter );
	           System.out.println();
	        }
	        rs.close();
	        stmt.close();
	        this.con.close();
		} 
		catch ( Exception e ) 
		{
	        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	        System.exit(0);
		}
		System.out.println("Operation done successfully");
    }
	
	/*
	 * Devolvemos 1 si se realizo con exito
	 * Devolvemos 0 si existio algun error
	 */
	public void selectContacto()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	
	        String id = Integer.toString(this.contact.getId());
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM contacto WHERE id = "+ id +";" );
	        while ( rs.next() ) 
	        {
	           int Id = rs.getInt("id");
	           String  email = rs.getString("email");
	           int phone  = rs.getInt("telefono");
	           String  twitter = rs.getString("twitter");
	           
	           System.out.println( "ID = " + Id );
	           System.out.println( "Email = " + email );
	           System.out.println( "Telefono = " + phone );
	           System.out.println( "twitter = @" + twitter );
	           System.out.println();
	        }
	        rs.close();
	        stmt.close();
	        this.con.close();
		} 
		catch ( Exception e ) 
		{
	        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	        System.exit(0);
		}
		System.out.println("Operation done successfully");
    }
	
	/*
	 * Devolvemos 1 si se realizo con exito
	 * Devolvemos 0 si existio algun error
	 */
	public void insertContacto()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    String id = Integer.toString(this.contact.getId());
			 String email = this.contact.getEmail();
			 String phone = Integer.toString(this.contact.getTelefono());
			 String twitter = this.contact.getTwitter();
			 
			 String sql = "INSERT INTO contacto (id, telefono, email, twitter) " + "VALUES (" + id + "," + phone +", '" + email + "', '" + twitter + "');";
		     stmt.executeUpdate(sql);
		
		     stmt.close();
		     this.con.commit();
		     this.con.close();
		  } 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}
	
	/*
	 * Devolvemos 1 si se realizo con exito
	 * Devolvemos 0 si existio algun error
	 */
	public void updateContacto()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    String id = Integer.toString(this.contact.getId());
		    String email = this.contact.getEmail();
			String phone = Integer.toString(this.contact.getTelefono());
			String twitter = this.contact.getTwitter();
			 
			String sql = "UPDATE contacto SET telefono=" + phone + ", email='" + email + "', twitter='" + twitter +  "' WHERE id=" + id + ";";
			stmt.executeUpdate(sql);
		
			stmt.close();
			this.con.commit();
			this.con.close();
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}
	
	/*
	 * Devolvemos 1 si se realizo con exito
	 * Devolvemos 0 si existio algun error
	 */
	public void deleteContacto()
	{
       Statement stmt = null;
       try 
       {
    	   this.con.setAutoCommit(false);
	       System.out.println("Opened database successfully");

	       String id = Integer.toString(this.contact.getId());
	       
	       stmt = this.con.createStatement();
	       String sql = "DELETE from contacto where id =" + id +";";
	       stmt.executeUpdate(sql);
	       this.con.commit();

	       ResultSet rs = stmt.executeQuery( "SELECT * FROM contacto;" );
	       while ( rs.next() ) 
	       {
	           int Id = rs.getInt("id");
	           String  email = rs.getString("email");
	           int phone  = rs.getInt("telefono");
	           String  twitter = rs.getString("twitter");
	           
	           System.out.println( "ID = " + Id );
	           System.out.println( "Email = " + email );
	           System.out.println( "Telefono = " + phone );
	           System.out.println( "twitter = @" + twitter );
	           System.out.println();
			}
			rs.close();
			stmt.close();
			this.con.close();
       } 
       catch ( Exception e ) 
       {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Operation done successfully");
	}
	
}
