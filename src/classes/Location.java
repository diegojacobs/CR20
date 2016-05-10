package classes;

/**
@author Diego Jacobs
Date: May 7, 2016
 */
public class Location {
	private int id;
	private String ciudad;
	private String pais;
	private int codigoPostal;
	
	public Location() {
		this.id = -1;
		this.ciudad = new String();
		this.pais = new String();
		this.codigoPostal = -1;
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
}
