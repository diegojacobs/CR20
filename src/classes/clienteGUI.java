package classes;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class clienteGUI extends JFrame implements ActionListener {
	
	JTextField nombre_1, nombre_2, apellido_1, apellido_2 ;
	JPanel content_pane;
	
	public clienteGUI(){
		initialize();
	}
	
	private void initialize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.setBounds(100, 100, (width/2), (height/2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		content_pane = new JPanel();
		
		JScrollPane window = new JScrollPane();
		this.getContentPane().add(window, BorderLayout.CENTER);
		window.setViewportView(content_pane);
		
		GridBagConstraints constraints = new GridBagConstraints();
	    GridBagLayout layout = new GridBagLayout();
	    content_pane.setLayout(layout);
	    
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.gridy = 0;
		
		JScrollPane temp1 = new JScrollPane();
		
		nombre_1 = new JTextField(10);
		temp1.setViewportView(nombre_1);
		//nombre_1.setText("Hola Mundo");
		new TextPrompt("Primer Nombre", nombre_1);
		
		content_pane.add(temp1, constraints);
		
		
		JScrollPane temp2 = new JScrollPane();
		
		nombre_2 = new JTextField(10);
		temp2.setViewportView(nombre_2);
		//nombre_2.setText("Hola Mundo");
		new TextPrompt("Segundo Nombre", nombre_2);
		
		content_pane.add(temp2, constraints);
		
		
		constraints.gridy = 1;
		JScrollPane temp3 = new JScrollPane();
		
		apellido_1 = new JTextField(10);
		temp3.setViewportView(apellido_1);
		//apellido_1.setText("Hola Mundo");
		new TextPrompt("Primer Apellido", apellido_1);
		
		content_pane.add(temp3,constraints);
		
		
		JScrollPane temp4 = new JScrollPane();
		
		apellido_2 = new JTextField(10);
		temp4.setViewportView(apellido_2);
		//apellido_2.setText("Hola Mundo");
		new TextPrompt("Segundo Apellido", apellido_2);
		
		content_pane.add(temp4,constraints);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
