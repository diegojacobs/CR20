package classes;

/**
@author Diego Jacobs
Date: May 7, 2016
 */
public class Estado {
	private int id;
	private String descripcion;
	
	public Estado() {
		this.id = -1;
		this.descripcion = new String();
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
	
}
