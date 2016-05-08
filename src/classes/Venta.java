package classes;

/**
@author Diego Jacobs
Date: May 7, 2016
 */
public class Venta {
	private int id;
	private int productoID;
	private int cantidad;
	private int facturaID;
	private double total;
	
	public Venta() {
		this.id = -1;
		this.productoID = -1;
		this.cantidad = -1;
		this.facturaID = -1;
		this.total = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductoID() {
		return productoID;
	}

	public void setProductoID(int productoID) {
		this.productoID = productoID;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getFacturaID() {
		return facturaID;
	}

	public void setFacturaID(int facturaID) {
		this.facturaID = facturaID;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
