package classes;

/**
@author Diego Jacobs
Date: May 7, 2016
 */
public class Producto {
	private int id;
	private String name;
	private double precio;
	
	public Producto() {
		this.id = -1;
		this.name = new String();
		this.precio = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double prize) {
		this.precio = prize;
	}
	
	
}
