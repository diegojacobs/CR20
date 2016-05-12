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
public class Location {
	private int id;
	private String ciudad;
	private String pais;
	private int codigoPostal;
	private String direccion;
	private Connection con;
	private ArrayList<Location> all;
	
	public Location() {
		this.id = -1;
		this.ciudad = new String();
		this.pais = new String();
		this.codigoPostal = -1;
		this.all = new ArrayList<Location>();
	}

	public Location(String ciudad, String pais, int codigoPostal, String direccion) {
		super();
		this.ciudad = ciudad;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.all = new ArrayList<Location>();
	}

	public Location(String ciudad, String pais, int codigoPostal, String direccion, myConnection con) {
		super();
		this.ciudad = ciudad;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.con = con.getCon();
		this.all = new ArrayList<Location>();
	}

	

	public Location(int id, String ciudad, String pais, int codigoPostal, String direccion,
			myConnection con) {
		super();
		this.id = id;
		this.ciudad = ciudad;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.con = con.getCon();
		this.all = new ArrayList<Location>();
	}

	public Location(int id, String ciudad, String pais, int codigoPostal, String direccion) {
		super();
		this.id = id;
		this.ciudad = ciudad;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.all = new ArrayList<Location>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public ArrayList<Location> getAll() {
		return all;
	}

	public void setAll(ArrayList<Location> all) {
		this.all = all;
	}
	
	/*
	 * Devolvemos null si se realizo con exito
	 * Devolvemos el error si existio alguno
	 */
	public String selectAllLocations()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM location;" );
	        while ( rs.next() ) 
	        {
	           int Id = rs.getInt("id");
	           String  ciudad = rs.getString("ciudad");
	           String  pais = rs.getString("pais");
	           int code  = rs.getInt("zipcode");
	           String  direccion = rs.getString("direccion");
	           
	           this.all.add(new Location(Id, ciudad, pais, code, direccion));
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
	public String selectAllPaises()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT distinct pais "
	        		+ "FROM location;" );
	        while ( rs.next() ) 
	        {
	           int Id = 0;
	           String ciudad = "ciudad";
	           String pais = rs.getString("pais");
	           int code  = 0;
	           String  direccion = "direccion";
	           
	           this.all.add(new Location(Id, ciudad, pais, code, direccion));
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
	public String selectPaisesMasVentas()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "select location.pais, count(venta.id) as totalVentas "
	        		+ "from venta join cliente on (venta.cliente_id = cliente.id) join location on (cliente.location_id = location.id) "
	        		+ "group by location.pais "
	        		+ "order by totalVentas desc;" );
	        while ( rs.next() ) 
	        {
	           int Id = 0;
	           String ciudad = "ciudad";
	           String pais = rs.getString("pais");
	           int code  = rs.getInt("totalventas");
	           String  direccion = "direccion";
	           
	           this.all.add(new Location(Id, ciudad, pais, code, direccion));
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
	public String selectPaisesMasClientes()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "select location.pais, count(cliente.id) as totalClientes "
	        		+ "from cliente join location on (cliente.location_id = location.id) "	        		
	        		+ "group by location.pais "
	        		+ "order by totalClientes desc;" );
	        while ( rs.next() ) 
	        {
	           int Id = 0;
	           String ciudad = "ciudad";
	           String pais = rs.getString("pais");
	           int code  = rs.getInt("totalclientes");
	           String  direccion = "direccion";
	           
	           this.all.add(new Location(Id, ciudad, pais, code, direccion));
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
	public String selectLocation()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	
	        String id = Integer.toString(this.getId());
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM location WHERE id = "+ id +";" );
	        while ( rs.next() ) 
	        {
	        	int Id = rs.getInt("id");
		           String  ciudad = rs.getString("ciudad");
		           String  pais = rs.getString("pais");
		           int code  = rs.getInt("zipcode");
		           String  direccion = rs.getString("direccion");
	           
	           this.setId(Id);
	           this.setCiudad(ciudad);
	           this.setPais(pais);
	           this.setCodigoPostal(code);
	           this.setDireccion(direccion);
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
	public String insertLocation()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery( "SELECT id FROM location WHERE id >= ALL (SELECT id FROM location);" );
		    
		    if (rs.next())
		    {
			    String id = Integer.toString(rs.getInt("id")+1);
			    this.id = Integer.parseInt(id);
				String pais = this.getPais();
				String code = Integer.toString(this.getCodigoPostal());
				String ciudad = this.getCiudad();
				String direccion = this.getDireccion();
				 
				String sql = "INSERT INTO location (id, zipcode, pais, ciudad, direccion) " + "VALUES (" + id + "," + code +", '" + pais + "', '" + ciudad + "', '"  + direccion + "');";
			    stmt.executeUpdate(sql);
		    }
		    else
		    {
		    	String id = Integer.toString(1);
			    this.id = Integer.parseInt(id);
				String pais = this.getPais();
				String code = Integer.toString(this.getCodigoPostal());
				String ciudad = this.getCiudad();
				String direccion = this.getDireccion();
				 
				String sql = "INSERT INTO location (id, zipcode, pais, ciudad, direccion) " + "VALUES (" + id + "," + code +", '" + pais + "', '" + ciudad + "', '"  + direccion + "');";
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
	public String updateLocation()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    String id = Integer.toString(this.getId());
		    String pais = this.getPais();
			String code = Integer.toString(this.getCodigoPostal());
			String ciudad = this.getCiudad();
			String direccion = this.getDireccion();
			 
			String sql = "UPDATE location SET zipcode=" + code + ", pais='" + pais + "', ciudad='" + ciudad +  "', direccion='" + direccion + "' WHERE id=" + id + ";";
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
	public String deleteLocation()
	{
       Statement stmt = null;
       try 
       {
    	   this.con.setAutoCommit(false);
	       System.out.println("Opened database successfully");

	       String id = Integer.toString(this.getId());
	       
	       stmt = this.con.createStatement();
	       String sql = "DELETE from location where id =" + id +";";
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
