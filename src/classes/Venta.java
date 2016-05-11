package classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectionDB.myConnection;

/**
@author Diego Jacobs
Date: May 7, 2016
 */
public class Venta {
	private int id;
	private int pagoID;
	private int clienteID;
	private int cantidad;
	private double total;
	private Date dia;
	private Connection con;
	private ArrayList<Venta> all;
	
	public Venta() {
		this.id = -1;
		this.pagoID = -1;
		this.cantidad = -1;
		this.clienteID = -1;
		this.total = 0;
		this.dia = null;
		this.all = new ArrayList<Venta>();
	}
	
	public Venta(int pagoID, int clienteID, int cantidad, double total, Date dia) {
		this.pagoID = pagoID;
		this.clienteID = clienteID;
		this.cantidad = cantidad;
		this.total = total;
		this.dia = dia;
		this.all = new ArrayList<Venta>();
	}
	
	public Venta(int pagoID, int clienteID, int cantidad, double total,
			Date dia, myConnection con) {
		super();
		this.pagoID = pagoID;
		this.clienteID = clienteID;
		this.cantidad = cantidad;
		this.total = total;
		this.dia = dia;
		this.con = con.getCon();
		this.all = new ArrayList<Venta>();
	}

	public Venta(int id, int pagoID, int clienteID, int cantidad, double total,
			Date dia) {
		this.id = id;
		this.pagoID = pagoID;
		this.clienteID = clienteID;
		this.cantidad = cantidad;
		this.total = total;
		this.dia = dia;
	}

	
	public Venta(int id, int pagoID, int clienteID, int cantidad, double total,
			Date dia, myConnection con) {
		this.id = id;
		this.pagoID = pagoID;
		this.clienteID = clienteID;
		this.cantidad = cantidad;
		this.total = total;
		this.dia = dia;
		this.con = con.getCon();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPagoID() {
		return pagoID;
	}

	public void setPagoID(int pagoID) {
		this.pagoID = pagoID;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getClienteID() {
		return clienteID;
	}

	public void setClienteID(int clienteID) {
		this.clienteID = clienteID;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public ArrayList<Venta> getAll() {
		return all;
	}

	public void setAll(ArrayList<Venta> all) {
		this.all = all;
	}

	/*
	 * Devolvemos null si se realizo con exito
	 * Devolvemos el error si existio alguno
	 */
	public String selectAllVentas()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM venta;" );
	        while ( rs.next() ) 
	        {
	           int Id = rs.getInt("id");
	           int pagoID = rs.getInt("pago_id");
	           int clienteID  = rs.getInt("cliente_id");
	           int  cantidad = rs.getInt("cantidad");
	           double total = rs.getDouble("total");
	           Date dia = rs.getDate("dia");
	           this.all.add(new Venta(Id, pagoID, clienteID, cantidad, total, dia));
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
	public String selectVenta()
	{
		Statement stmt = null;
		
		try
		{
			this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	
	        String id = Integer.toString(this.getClienteID());
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM venta WHERE cliente_id = "+ id +";" );
	        while ( rs.next() ) 
	        {
	        	int Id = rs.getInt("id");
	        	int pagoID = rs.getInt("pago_id");
	        	int clienteID  = rs.getInt("cliente_id");
	        	int  cantidad = rs.getInt("cantidad");
	        	double total = rs.getDouble("total");
	        	Date dia = rs.getDate("dia");
	           
	        	this.all.add(new Venta(Id, pagoID, clienteID, cantidad, total, dia));
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
	public String insertVenta()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery( "SELECT id FROM venta WHERE id >= ALL (SELECT id FROM venta);" );
		    
		    if (rs.next())
		    {
			    String id = Integer.toString(rs.getInt("id")+1);
				String clienteID = Integer.toString(this.getClienteID());
				String pagoID = Integer.toString(this.getPagoID());
				String cantidad = Integer.toString(this.getCantidad());
				String total = Double.toString(this.getTotal());
				String dia = this.getDia().toString();
				 
				String sql = "INSERT INTO venta (id, cliente_id, pago_id, cantidad, total, dia) " + "VALUES (" + id + "," + clienteID +", " + pagoID + ", " + cantidad +  ", " + total + ", '" + dia + "');";
			    stmt.executeUpdate(sql);
		    }
		    else
		    {
		    	String id = "1";
				String clienteID = Integer.toString(this.getClienteID());
				String pagoID = Integer.toString(this.getPagoID());
				String cantidad = Integer.toString(this.getCantidad());
				String total = Double.toString(this.getTotal());
				String dia = this.getDia().toString();
				 
				String sql = "INSERT INTO venta (id, cliente_id, pago_id, cantidad, total, dia) " + "VALUES (" + id + "," + clienteID +", " + pagoID + ", " + cantidad +  ", " + total + ", '" + dia + "');";
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
	public String updateVenta()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    String id = Integer.toString(this.getId());
			String clienteID = Integer.toString(this.getClienteID());
			String pagoID = Integer.toString(this.getPagoID());
			String cantidad = Integer.toString(this.getCantidad());
			String total = Double.toString(this.getTotal());
			String dia = this.getDia().toString();
			 
			String sql = "UPDATE venta SET cliente_id=" + clienteID + ", pago_id=" + pagoID + ", cantidad=" + cantidad +  ", total=" + total + ", dia='" + dia + "' WHERE id=" + id + ";";
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
	public String deleteVenta()
	{
       Statement stmt = null;
       try 
       {
    	   this.con.setAutoCommit(false);
	       System.out.println("Opened database successfully");

	       String id = Integer.toString(this.getId());
	       
	       stmt = this.con.createStatement();
	       String sql = "DELETE from venta where id =" + id +";";
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
