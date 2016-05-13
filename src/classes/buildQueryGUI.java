package classes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import connectionDB.myConnection;

public class buildQueryGUI extends JDialog implements ActionListener{
	Vector<String> columnNames_public;
	String table_name;
	String query;
	ArrayList<JTextField> campos = new ArrayList();
	private JPanel content_pane;
	private JTextField descripcion;
	private JButton guardar, cancelar;
	
	public buildQueryGUI(Vector<String> columns, String table){
		super((JDialog)null, "Query", true);
		
		this.columnNames_public = columns;
		this.table_name = table;
		initialize();
	}
	
	public void initialize(){

		
		int posy = 10;
		
		
		content_pane = new JPanel();
		
		//content_pane.setBounds(0,0, 400, 400);
		this.getContentPane().add(content_pane);
		
		//this.setVisible(true);
		
		content_pane.setLayout(null);

		for (int i = 0; i < columnNames_public.size(); i++){
			JLabel temp_label = new JLabel(columnNames_public.get(i)+"= ");
			temp_label.setBounds(10,posy,150,25);
			
			JTextField temp_textField = new JTextField();
			new TextPrompt(columnNames_public.get(i), temp_textField);

			temp_textField.setBounds(160,posy,150,25);
			posy += 30;
			
			content_pane.add(temp_label);
			content_pane.add(temp_textField);
			campos.add(temp_textField);
		}
		
		guardar = new JButton("Guardar");
		guardar.setToolTipText("Guardar Estado");
		guardar.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		guardar.setBorder(BorderFactory.createEmptyBorder());
		guardar.setBounds(200,posy,70,25);
		content_pane.add(guardar);
		
		cancelar = new JButton("Cancelar");
		cancelar.setToolTipText("Omitir Estado");
		cancelar.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		cancelar.setBorder(BorderFactory.createEmptyBorder());
		cancelar.setBounds(120,posy,70,25);
		content_pane.add(cancelar);
		
		posy+= 100;
		
		this.setPreferredSize(new Dimension(350,posy));
		content_pane.setBounds(100, 100, 350, posy);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == guardar){
			query = "";
			query+= "select * from "+table_name+" where ";
			
			boolean first = true;
			for (int i = 0; i < campos.size(); i++){
				if (!campos.get(i).getText().isEmpty()){
					if (first){
						first = false;
					}else{
						query += ",";
					}
					query += columnNames_public.get(i) +" = " +campos.get(i).getText();
					
				}
			}
			this.dispose();
		
			//System.out.println("1 "+str_telefono+" 2 "+str_email+" 3 "+str_twitter);
		}else if (e.getSource() == cancelar){
			//System.out.println("cancelar");
			this.dispose();
			//this.setVisible(false);
		}
		
	}
	
	public Object showDialog(){
		this.setVisible(true);
		return query;
	}

}
