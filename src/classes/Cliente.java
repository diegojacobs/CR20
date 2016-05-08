package classes;

import java.sql.Date;


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
	private boolean genero;
	private int estadoCivilID;
	
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
	
}
