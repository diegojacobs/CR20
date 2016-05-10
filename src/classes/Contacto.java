package classes;

/**
@author Diego Jacobs
Date: May 7, 2016
 */
public class Contacto {
	private int id;
	private int telefono;
	private String email;
	private String twitter;

	public Contacto() {
		this.id = -1;
		this.telefono = -1;
		this.email = new String();
		this.twitter = new String();
	}

	public Contacto(int telefono, String email, String twitter) {
		super();
		this.telefono = telefono;
		this.email = email;
		this.twitter = twitter;
	}

	public Contacto(int id, int telefono, String email, String twitter) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.email = email;
		this.twitter = twitter;
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
	
	
}
