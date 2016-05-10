package classes;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class clienteGUI extends JFrame implements ActionListener {
	
	JTextField nombre_1, nombre_2, apellido_1, apellido_2 ;
	JPanel content_pane;
	JDateChooser fecha_nacimiento;
	Choice location, estado;
	
	public clienteGUI(){
		initialize();
	}
	
	private void initialize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.setBounds(100, 100, (width/2), (height/2));
		//this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		content_pane = new JPanel();
		
		JScrollPane window = new JScrollPane();
		this.getContentPane().add(window, BorderLayout.CENTER);
		window.setViewportView(content_pane);
		
		content_pane.setLayout(null);
		
		JLabel nombre_1_label = new JLabel("Primer Nombre:");
		nombre_1_label.setBounds(10,10,100, 25);
		content_pane.add(nombre_1_label);
		
		nombre_1 = new JTextField();
		//nombre_1.setText("Hola Mundo");
		new TextPrompt("Primer Nombre", nombre_1);
		nombre_1.setBounds(120,10,150,25);
		content_pane.add(nombre_1);
		
		JLabel nombre_2_label = new JLabel("Segundo Nombre:");
		nombre_2_label.setBounds(280,10,110, 25);
		content_pane.add(nombre_2_label);
		
		nombre_2 = new JTextField(10);
		//nombre_2.setText("Hola Mundo");
		new TextPrompt("Segundo Nombre", nombre_2);
		nombre_2.setBounds(400,10,150,25);
		content_pane.add(nombre_2);
		
		JLabel apellido_1_label = new JLabel("Primer Apellido:");
		apellido_1_label.setBounds(10,40,100, 25);
		content_pane.add(apellido_1_label);
		
		apellido_1 = new JTextField(10);
		//apellido_1.setText("Hola Mundo");
		new TextPrompt("Primer Apellido", apellido_1);
		apellido_1.setBounds(120,40,150,25);
		content_pane.add(apellido_1);
		
		JLabel apellido_2_label = new JLabel("Segundo Apellido:");
		apellido_2_label.setBounds(280,40,110, 25);
		content_pane.add(apellido_2_label);
		
		apellido_2 = new JTextField(10);
		//apellido_2.setText("Hola Mundo");
		new TextPrompt("Segundo Apellido", apellido_2);
		apellido_2.setBounds(400,40,150,25);
		content_pane.add(apellido_2);
		
		
		JLabel fecha_nacimiento_label = new JLabel("Fecha de Nacimiento:");
		fecha_nacimiento_label.setBounds(10,70,130, 25);
		content_pane.add(fecha_nacimiento_label);
		
		
		Date current_date = new Date();
		fecha_nacimiento = new JDateChooser();
		fecha_nacimiento.setLocale(Locale.US);
		fecha_nacimiento.setDate(current_date);
		fecha_nacimiento.setBounds(150, 70, 130, 25);
		
		Date this_date = fecha_nacimiento.getDate();
		//String fecha_obtenida = df.format(fecha_nacimiento.getDate());
		//DateFormat df = DateFormat.getDateInstance();
		content_pane.add(fecha_nacimiento);
		
		
		JLabel estado_label = new JLabel("Estado:");
		estado_label.setBounds(10,100,130, 25);
		content_pane.add(estado_label);
		
		estado = new Choice();
		estado.setBounds(150,100 , 130, 25);
		estado.add("prueba");
		//estado.getSelectedIndex();
		//estado.select(position);
		//estado.select("algo");
		content_pane.add(estado);
		
		
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
