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
public class Cliente {
	private int id;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Date nacimiento;
	private int locationID;
	private int contactoID;
	private int estadoID;
	private double descuento;
	private String imagen; //Direccion donde se guarda la imagen
	private boolean genero; // es true si es hombre, false mujer
	private int estadoCivilID;
	private Connection con;
	private ArrayList<Cliente> all;
	
	public Cliente() {
		this.id=-1;
		this.primerNombre = new String();
		this.segundoNombre = new String();
		this.primerApellido = new String();
		this.segundoApellido = new String();
		this.nacimiento = null;
		this.locationID = -1;
		this.contactoID = -1;
		this.estadoID = -1;
		this.descuento = 0;
		this.imagen = new String();
		this.genero = false;
		this.estadoCivilID = -1;
		this.all = new ArrayList<Cliente>();
	}
	
	public Cliente(String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, Date nacimiento,
			int locationID, int contactoID, int estadoID, double descuento,
			String imagen, boolean genero, int estadoCivilID) {
		super();
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.nacimiento = nacimiento;
		this.locationID = locationID;
		this.contactoID = contactoID;
		this.estadoID = estadoID;
		this.descuento = descuento;
		this.imagen = imagen;
		this.genero = genero;
		this.estadoCivilID = estadoCivilID;
		this.all = new ArrayList<Cliente>();
	}

	

	public Cliente(String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, Date nacimiento,
			int locationID, int contactoID, int estadoID, double descuento,
			String imagen, boolean genero, int estadoCivilID, myConnection con) {
		super();
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.nacimiento = nacimiento;
		this.locationID = locationID;
		this.contactoID = contactoID;
		this.estadoID = estadoID;
		this.descuento = descuento;
		this.imagen = imagen;
		this.genero = genero;
		this.estadoCivilID = estadoCivilID;
		this.con = con.getCon();
		this.all = new ArrayList<Cliente>();
	}

	
	public Cliente(int id, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, Date nacimiento,
			int locationID, int contactoID, int estadoID, double descuento,
			String imagen, boolean genero, int estadoCivilID) {
		super();
		this.id = id;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.nacimiento = nacimiento;
		this.locationID = locationID;
		this.contactoID = contactoID;
		this.estadoID = estadoID;
		this.descuento = descuento;
		this.imagen = imagen;
		this.genero = genero;
		this.estadoCivilID = estadoCivilID;
		this.all = new ArrayList<Cliente>();
	}

	
	public Cliente(int id, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, Date nacimiento,
			int locationID, int contactoID, int estadoID, double descuento,
			String imagen, boolean genero, int estadoCivilID, myConnection con) {
		super();
		this.id = id;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.nacimiento = nacimiento;
		this.locationID = locationID;
		this.contactoID = contactoID;
		this.estadoID = estadoID;
		this.descuento = descuento;
		this.imagen = imagen;
		this.genero = genero;
		this.estadoCivilID = estadoCivilID;
		this.con = con.getCon();
		this.all = new ArrayList<Cliente>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public int getContactoID() {
		return contactoID;
	}

	public void setContactoID(int contactoID) {
		this.contactoID = contactoID;
	}

	public int getEstadoID() {
		return estadoID;
	}

	public void setEstadoID(int estadoID) {
		this.estadoID = estadoID;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}

	public int getEstadoCivilID() {
		return estadoCivilID;
	}

	public void setEstadoCivilID(int estadoCivilID) {
		this.estadoCivilID = estadoCivilID;
	}
	
	public String getNombreCompleto()
	{
		return this.primerNombre + " "+this.segundoNombre + " " + this.primerApellido+" " + this.segundoApellido;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public ArrayList<Cliente> getAll() {
		return all;
	}

	public void setAll(ArrayList<Cliente> all) {
		this.all = all;
	}
	
	/*
	 * Devolvemos null si se realizo con exito
	 * Devolvemos el error si existio alguno
	 */
	public String selectAllClientes()
	{
		Statement stmt = null;
		
		try
		{
			//this.con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
	        stmt = this.con.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM cliente;" );
	        while ( rs.next() ) 
	        {
	           int Id = rs.getInt("id");
	           String primern = rs.getString("primer_nombre");
	           String segundon = rs.getString("segundo_nombre");
	           String primera = rs.getString("primer_apellido");
	           String segundoa = rs.getString("segundo_apellido");
	           Date fecha = rs.getDate("fecha_de_nacimiento");
	           int locationID = rs.getInt("location_id");
	           int stateID = rs.getInt("estado_id");
	           int contactoID = rs.getInt("contacto_id");
	           Double desc = rs.getDouble("descuento");
	           String  image = rs.getString("image");
	           boolean genero = rs.getBoolean("genero");
	           int estadoCivilID  = rs.getInt("estado_civil_id");
	           
	           this.all.add(new Cliente(Id, primern, segundon, primera, segundoa, fecha, locationID, contactoID, stateID, desc, image, genero, estadoCivilID));
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
	public String selectCliente()
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
	            String primern = rs.getString("primer_nombre");
	            String segundon = rs.getString("segundo_nombre");
	            String primera = rs.getString("primer_apellido");
	            String segundoa = rs.getString("segundo_apellido");
	            Date fecha = rs.getDate("fecha_de_nacimiento");
	            int locationID = rs.getInt("location_id");
	            int stateID = rs.getInt("estado_id");
	            int contactoID = rs.getInt("contacto_id");
	            Double desc = rs.getDouble("descuento");
	            String  image = rs.getString("image");
	            boolean genero = rs.getBoolean("genero");
	            int estadoCivilID  = rs.getInt("estado_civil_id");
	           
	            this.setId(Id);
	            this.setPrimerNombre(primern);
	            this.setSegundoNombre(segundon);
	            this.setPrimerApellido(primera);
	            this.setSegundoApellido(segundoa);
	            this.setNacimiento(fecha);
	            this.setLocationID(locationID);
	            this.setEstadoID(stateID);
	            this.setContactoID(contactoID);
	            this.setDescuento(desc);
	            this.setImagen(image);
	            this.setGenero(genero);
	            this.setEstadoCivilID(estadoCivilID);
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
	public String insertCliente()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery( "SELECT id FROM cliente WHERE id >= ALL (SELECT id FROM cliente);" );
		    
		    if (rs.next())
		    {
			    String id = Integer.toString(rs.getInt("id")+1);
			    String primern = this.getPrimerNombre();
			    String segundon = this.getSegundoNombre();
			    String primera = this.getPrimerApellido();
			    String segundoa = this.getSegundoApellido();
			    String fecha = this.getNacimiento().toString();
			    String locID = Integer.toString(this.getLocationID());
			    String conID = Integer.toString(this.getContactoID());
			    String estID = Integer.toString(this.getEstadoID());
			    String desc = Double.toString(this.getDescuento());
			    String imagen = this.getImagen();
			    String gen = Boolean.toString(this.isGenero());
			    String estCID = Integer.toString(this.getEstadoCivilID());
			    
				 
				String sql = "INSERT INTO cliente(id, primer_nombre, segundo_nombre, primer_apellido,"
						+ "segundo_apellido, fecha_de_nacimineto, location_id, contacto_id, estado_id,"
						+ "descuento, image, genero, estado_civil_id)    VALUES ("
						+ id + ", '" + primern +"', '" + segundon + "', '" + primera + "', '" + segundoa + "', '" 
						+ fecha + "', " + locID + ", " + conID +", " + estID + ", " + desc + ", '" 
						+ imagen + "', " + gen + ", " + estCID + ");";
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
	public String updateCliente()
	{
		Statement stmt = null;
		try 
		{
			this.con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = this.con.createStatement();
		    
		    String id = Integer.toString(this.getId());
		    String primern = this.getPrimerNombre();
		    String segundon = this.getSegundoNombre();
		    String primera = this.getPrimerApellido();
		    String segundoa = this.getSegundoApellido();
		    String fecha = this.getNacimiento().toString();
		    String locID = Integer.toString(this.getLocationID());
		    String conID = Integer.toString(this.getContactoID());
		    String estID = Integer.toString(this.getEstadoID());
		    String desc = Double.toString(this.getDescuento());
		    String imagen = this.getImagen();
		    String gen = Boolean.toString(this.isGenero());
		    String estCID = Integer.toString(this.getEstadoCivilID());
			 
			String sql = "UPDATE cliente SET "
					+ "primer_nombre='" + primern + "', segundo_nombre='" + segundon + "', primer_apellido='" + primera 
					+ "', segundo_apellido='" + segundoa + "', fecha_de_nacimiento='" + fecha 
					+ "', location_id=" + locID + ", contacto_id=" + conID + ", estado_id=" + estID 
					+ ", descuento=" + desc + ", imagen='" + imagen + "', genero=" + gen 
					+ ", estado_civil_id=" + estCID  + " WHERE id=" + id + ";";
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
	public String deleteCliente()
	{
       Statement stmt = null;
       try 
       {
    	   this.con.setAutoCommit(false);
	       System.out.println("Opened database successfully");

	       String id = Integer.toString(this.getId());
	       
	       stmt = this.con.createStatement();
	       String sql = "DELETE from cliente where id =" + id +";";
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
