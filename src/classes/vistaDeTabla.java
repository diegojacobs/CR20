package classes;

import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;




import javax.swing.table.DefaultTableModel;

import connectionDB.myConnection;

public class vistaDeTabla extends JFrame {
	
	private String tipo_tabla;

	public vistaDeTabla(String tipo_tabla){
		this.tipo_tabla = tipo_tabla;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.setBounds(100, 100, (width/2), (height/2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		algo();
	}
	
	public void algo(){
		JTable table = null;
		try{
			
			Connection con = new myConnection("postgres","root").getCon();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM "+tipo_tabla+";" );

		    table = new JTable(buildTableModel(rs));
		    
		    
		}catch (Exception e){
			
		}
		if (table != null){

		    JScrollPane scrollPane_1 = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    this.getContentPane().add(scrollPane_1);
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
	}
	
	
	public DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
