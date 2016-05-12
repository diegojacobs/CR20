package classes;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;




import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import connectionDB.myConnection;

public class vistaDeTabla extends JFrame {
	
	private String tipo_tabla;
	private Object objeto;
	private ArrayList<JCheckBox> columns = new ArrayList();
	private JTable table = null;

	public vistaDeTabla(String tipo_tabla){
		this.tipo_tabla = tipo_tabla;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.setBounds(100, 100, (width/2), (height/2));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newInstance(tipo_tabla);
		algo();
	}
	
	public void newInstance(String tipo){
		switch(tipo){
		case "cliente":
			Cliente cliente = new Cliente();
			myConnection connection = new myConnection("postgres","root");
			cliente.setCon(connection.getCon());
			cliente.selectAllClientes();
			objeto = cliente;
			break;
		case "contacto":
			Contacto contacto = new Contacto();
			connection = new myConnection("postgres","root");
			contacto.setCon(connection.getCon());
			contacto.selectAllContactos();
			objeto = contacto;
			break;
		case "estado":
			Estado estado = new Estado();
			connection = new myConnection("postgres","root");
			estado.setCon(connection.getCon());
			estado.selectAllEstados();
			objeto = estado;;
			break;
		case "estado_civil":
			EstadoCivil estado_civil = new EstadoCivil();
			connection = new myConnection("postgres","root");
			estado_civil.setCon(connection.getCon());
			estado_civil.selectAllEstadosCiviles();
			objeto = estado_civil;
			break;
		case "location":
			Location location = new Location();
			connection = new myConnection("postgres","root");
			location.setCon(connection.getCon());
			location.selectAllLocations();
			objeto = location;
			break;
		case "pago":
			Pago pago = new Pago();
			connection = new myConnection("postgres","root");
			pago.setCon(connection.getCon());
			pago.selectAllPagos();
			objeto = pago;
			break;
		case "venta":
			Venta venta = new Venta();
			connection = new myConnection("postgres","root");
			venta.setCon(connection.getCon());
			venta.selectAllVentas();
			objeto = venta;
			break;
		}
	}
	
	public void algo(){
		
		try{
			
			Connection con = new myConnection("postgres","root").getCon();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM "+tipo_tabla+";" );

		    table = new JTable(buildTableModel(rs));
		    table.setRowHeight(20);
		    
		    ButtonColumn buttoncolumn = new ButtonColumn(table,delete,table.getColumnCount()-1);
		    ButtonColumn buttoncolumn2 = new ButtonColumn(table,edit,table.getColumnCount()-2);
		    ButtonColumn buttoncolumn3;
		    if (tipo_tabla.equals("cliente") || tipo_tabla.equals("contacto"))
		    	buttoncolumn3 = new ButtonColumn (table, twitter, table.getColumnCount()-3);
		    
		    
		    
		}catch (Exception e){
			
		}
		if (table != null){

		    JScrollPane scrollPane_1 = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    this.getContentPane().add(scrollPane_1,BorderLayout.CENTER);
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    
		    JToolBar toolBar = new JToolBar();
			this.getContentPane().add(toolBar, BorderLayout.NORTH);
			
			
			for (JCheckBox temp: columns){
				temp.addItemListener(new ItemListener() {
		    	      public void itemStateChanged(ItemEvent e) {
    	    	        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
    	    	            //do something...
    	    	        	table.getColumnModel().getColumn(columns.indexOf(temp)).setMinWidth(20);
		    	        	table.getColumnModel().getColumn(columns.indexOf(temp)).setMaxWidth(300);
		    	        	table.getColumnModel().getColumn(columns.indexOf(temp)).setPreferredWidth(100);
    	    	        } else {//checkbox has been deselected
    	    	            //do something...
    	    	        	table.getColumnModel().getColumn(columns.indexOf(temp)).setMinWidth(0);
		    	        	table.getColumnModel().getColumn(columns.indexOf(temp)).setMaxWidth(0);
		    	        	table.getColumnModel().getColumn(columns.indexOf(temp)).setPreferredWidth(0);
		    	        	
    	    	        };
		    	   
		    	      }
		    	    });
				toolBar.add(temp);
				
			}
		}
	}
	
	Action delete = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			JTable table = (JTable)e.getSource();
			int modelRow = Integer.valueOf(e.getActionCommand());
			
			int reply = JOptionPane.showConfirmDialog(null,
					"¿Esta seguro que desea eliminar este Cliente?",
					"Confirmacion",
					JOptionPane.YES_NO_OPTION);
	        if (reply != JOptionPane.YES_OPTION) {
	        	return;
	        }
	        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
	        myConnection connection = new myConnection("postgres","root");
	        
			switch(tipo_tabla){
			case "cliente":
				Cliente cliente = (Cliente)objeto;
				connection = new myConnection("postgres","root");
				//cliente.setCon(connection.getCon());
				cliente.getAll().get(modelRow).setCon(connection.getCon());
				cliente.getAll().get(modelRow).deleteCliente();
				cliente.getAll().remove(modelRow);
				break;
			case "contacto":
				Contacto contacto = (Contacto)objeto;
				connection = new myConnection("postgres","root");
				contacto.getAll().get(modelRow).setCon(connection.getCon());
				contacto.getAll().get(modelRow).deleteContacto();
				contacto.getAll().remove(modelRow);
				break;
			case "estado":
				Estado estado = (Estado)objeto;
				connection = new myConnection("postgres","root");
				estado.getAll().get(modelRow).setCon(connection.getCon());
				estado.getAll().get(modelRow).deleteEstados();
				estado.getAll().remove(modelRow);
				break;
			case "estado_civil":
				EstadoCivil estado_civil = (EstadoCivil)objeto;
				connection = new myConnection("postgres","root");
				estado_civil.getAll().get(modelRow).setCon(connection.getCon());
				estado_civil.getAll().get(modelRow).deleteEstadoCivil();
				estado_civil.getAll().remove(modelRow);
				break;
			case "location":
				Location location = (Location)objeto;
				connection = new myConnection("postgres","root");
				location.getAll().get(modelRow).setCon(connection.getCon());
				location.getAll().get(modelRow).deleteLocation();
				location.getAll().remove(modelRow);
				break;
			case "pago":
				Pago pago = (Pago)objeto;
				connection = new myConnection("postgres","root");
				pago.getAll().get(modelRow).setCon(connection.getCon());
				pago.getAll().get(modelRow).deletePago();
				pago.getAll().remove(modelRow);
				break;
			case "venta":
				Venta venta = (Venta)objeto;
				connection = new myConnection("postgres","root");
				venta.getAll().get(modelRow).setCon(connection.getCon());
				venta.getAll().get(modelRow).deleteVenta();
				venta.getAll().remove(modelRow);
				break;
			}
		}
	};
	
	Action edit = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			JTable table = (JTable)e.getSource();
			int modelRow = Integer.valueOf(e.getActionCommand());
			
	        myConnection connection = new myConnection("postgres","root");
	        
			switch(tipo_tabla){
			case "cliente":
				Cliente cliente = (Cliente)objeto;
				connection = new myConnection("postgres","root");
				//cliente.setCon(connection.getCon());
				cliente.getAll().get(modelRow).setCon(connection.getCon());
				new clienteGUI((JFrame)null,cliente.getAll().get(modelRow)).showDialog();
				break;
			case "contacto":
				Contacto contacto = (Contacto)objeto;
				connection = new myConnection("postgres","root");
				contacto.getAll().get(modelRow).setCon(connection.getCon());
				new contactGUI((JFrame)null,contacto.getAll().get(modelRow)).showDialog();
				break;
			case "estado":
				Estado estado = (Estado)objeto;
				connection = new myConnection("postgres","root");
				estado.getAll().get(modelRow).setCon(connection.getCon());
				new estadoGUI((JFrame)null,estado.getAll().get(modelRow)).showDialog();
				break;
			case "estado_civil":
				EstadoCivil estado_civil = (EstadoCivil)objeto;
				connection = new myConnection("postgres","root");
				estado_civil.getAll().get(modelRow).setCon(connection.getCon());
				new estadoCivilGUI((JFrame)null,estado_civil.getAll().get(modelRow)).showDialog();
				break;
			case "location":
				Location location = (Location)objeto;
				connection = new myConnection("postgres","root");
				location.getAll().get(modelRow).setCon(connection.getCon());
				new LocationGUI((JFrame)null,location.getAll().get(modelRow)).showDialog();
				break;
			case "pago":
				Pago pago = (Pago)objeto;
				connection = new myConnection("postgres","root");
				pago.getAll().get(modelRow).setCon(connection.getCon());
				new PagoGUI((JFrame)null,pago.getAll().get(modelRow)).showDialog();
				break;
			case "venta":
				Venta venta = (Venta)objeto;
				connection = new myConnection("postgres","root");
				venta.getAll().get(modelRow).setCon(connection.getCon());
				new VentaGUI((JFrame)null,venta.getAll().get(modelRow)).showDialog();
				break;
			}
			((DefaultTableModel)table.getModel()).fireTableRowsUpdated(modelRow, modelRow);
		}
	};
	
	Action twitter = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			JTable table = (JTable)e.getSource();
			int modelRow = Integer.valueOf(e.getActionCommand());
			
	        myConnection connection = new myConnection("postgres","root");
	        
			switch(tipo_tabla){
			case "cliente":
				Cliente cliente = (Cliente)objeto;
				int id = cliente.getAll().get(modelRow).getContactoID();
				Contacto contacto = new Contacto();
				contacto.setId(id);
		        connection = new myConnection("postgres","root");
		        contacto.setCon(connection.getCon());
		        contacto.selectContacto();
		        if (contacto.getTwitter().isEmpty()){
		        	JOptionPane.showMessageDialog(null, "Twitter no definido");
		        }
				break;
			case "contacto":
				Contacto contacto2 = (Contacto)objeto;
				if (contacto2.getAll().get(modelRow).getTwitter().isEmpty()){

		        	JOptionPane.showMessageDialog(null, "Twitter no definido");
				}
				
				break;
			
			}
			((DefaultTableModel)table.getModel()).fireTableRowsUpdated(modelRow, modelRow);
		}
	};
	
	
	public DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	    	JCheckBox temp = new JCheckBox(metaData.getColumnName(column),true);
	    	
	    	columns.add(temp);
	        columnNames.add(metaData.getColumnName(column));
	    }
	    if (tipo_tabla.equals("contacto") || tipo_tabla.equals("cliente"))
	    	columnNames.add("Twitter");
	    columnNames.add("Editar");
	    columnNames.add("Eliminar");

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        if (tipo_tabla.equals("contacto") || tipo_tabla.equals("cliente"))
	        	vector.add("Twitter");
	        vector.add("Editar");
	        vector.add("Eliminar");
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
	
}
