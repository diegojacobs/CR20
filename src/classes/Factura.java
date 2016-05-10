package classes;

import java.sql.Date;

/**
@author Diego Jacobs
Date: May 7, 2016
 */
public class Factura {
	private int id;
	private int clienteID;
	private int pagoID;
	private Date dia;
	private String descripcion;
	private double total;
	
	public Factura() {
		this.id = -1;
		this.clienteID = -1;
		this.pagoID = -1;
		this.dia = null;
		this.descripcion = new String();
		this.total = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClienteID() {
		return clienteID;
	}

	public void setClienteID(int clienteID) {
		this.clienteID = clienteID;
	}

	public int getPagoID() {
		return pagoID;
	}

	public void setPagoID(int pagoID) {
		this.pagoID = pagoID;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
