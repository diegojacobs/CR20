package classes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.internet.InternetAddress;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import connectionDB.myConnection;

public class estadoCivilGUI extends JDialog implements ActionListener {
	
	private EstadoCivil estado;
	private JPanel content_pane;
	private JTextField descripcion;
	private JButton guardar, cancelar;
	
	public estadoCivilGUI(JFrame frame){
		super(frame, "Estado Civil", true);
		initialize();
	}
	
	public estadoCivilGUI(JDialog frame){
		super(frame, "Estado Civil", true);
		initialize();
	}
	
	public estadoCivilGUI(JFrame frame, EstadoCivil estado){
		super(frame, "Estado Civil", true);
		this.estado = estado;
		initialize();
		setValues(estado);
	}
	
	public estadoCivilGUI(JDialog frame, EstadoCivil estado){
		super(frame, "Estado Civil", true);
		this.estado = estado;
		initialize();
		setValues(estado);
	}
	
	public void setValues(EstadoCivil estado){
		descripcion.setText(estado.getDescripcion());
	}
	
	public void initialize(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		//this.setBounds(100, 100,730,360);// (width/2), (height/2));
		//this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 342, 158);
		this.setPreferredSize(new Dimension(300,115));
		
		content_pane = new JPanel();
		content_pane.setBounds(100, 100,300,115);
		//content_pane.setBounds(0,0, 400, 400);
		this.getContentPane().add(content_pane);
		
		//this.setVisible(true);

		
		JScrollPane window = new JScrollPane();
		this.getContentPane().add(window, BorderLayout.CENTER);
		window.setViewportView(content_pane);
		
		content_pane.setLayout(null);
		
		JLabel descripcion_label = new JLabel("Descripcion: ");
		descripcion_label.setBounds(10,10,100, 25);
		content_pane.add(descripcion_label);
		
		descripcion = new JTextField();
		//nombre_1.setText("Hola Mundo");
		new TextPrompt("Descripcion", descripcion);
		descripcion.setBounds(120,10,150,25);
		content_pane.add(descripcion);
		
		guardar = new JButton("Guardar");
		guardar.setToolTipText("Guardar Estado");
		guardar.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		guardar.setBorder(BorderFactory.createEmptyBorder());
		guardar.setBounds(200,40,70,25);
		content_pane.add(guardar);
		
		cancelar = new JButton("Cancelar");
		cancelar.setToolTipText("Omitir Estado");
		cancelar.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		cancelar.setBorder(BorderFactory.createEmptyBorder());
		cancelar.setBounds(120,40,70,25);
		content_pane.add(cancelar);

		this.pack();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == guardar){
			String str_descripcion = descripcion.getText();
			if (str_descripcion.isEmpty()){
				JOptionPane.showMessageDialog(this, "Descripcion es un campo obligatorio");
				return;
			}		   
		   
		   myConnection connection = new myConnection("postgres","root");
		   String insertStatus;
		   if (estado != null){
			   estado.setDescripcion(str_descripcion);
			   insertStatus = estado.updateEstadoCivil();
		   }else{
			   estado = new EstadoCivil(str_descripcion,connection);
			   insertStatus = estado.insertEstadoCivil();
		   }
		  
		   if (insertStatus != null)
				System.out.println(insertStatus);
				//JOptionPane.showMessageDialog(null, "Error en el ZipCode", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
		   else{
				JOptionPane.showMessageDialog(this, "Estado Civil guardado exitosamente", "Estado", JOptionPane.PLAIN_MESSAGE);
				this.dispose();
		   }
			//System.out.println("1 "+str_telefono+" 2 "+str_email+" 3 "+str_twitter);
		}else if (e.getSource() == cancelar){
			//System.out.println("cancelar");
			this.dispose();
			//this.setVisible(false);
		}
	}
	
	public Object showDialog(){
		this.setVisible(true);
		return estado;
	}

}
