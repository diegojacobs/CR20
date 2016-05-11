package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.internet.InternetAddress;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class contactGUI extends JFrame implements ActionListener {

	private JPanel content_pane;
	private JTextField telefono, email, twitter;
	private JButton guardar, cancelar;

	public contactGUI(){
		super("Contacto");
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		//this.setBounds(100, 100, (width/2), (height/2));
		this.setBounds(100,100,300,170);
		//this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		content_pane = new JPanel();
		content_pane.setLayout(null);
		
		JScrollPane window = new JScrollPane();
		this.getContentPane().add(window, BorderLayout.CENTER);
		window.setViewportView(content_pane);
		
		
		JLabel telefono_label = new JLabel("Telefono: ");
		telefono_label.setBounds(10,10,100, 25);
		content_pane.add(telefono_label);
		
		telefono = new JTextField();
		//nombre_1.setText("Hola Mundo");
		new TextPrompt("Telefono", telefono);
		telefono.setBounds(120,10,150,25);
		content_pane.add(telefono);
		
		JLabel email_label = new JLabel("e - mail: ");
		email_label.setBounds(10,40,100, 25);
		content_pane.add(email_label);
		
		email = new JTextField();
		//nombre_1.setText("Hola Mundo");
		new TextPrompt("e - mail", email);
		email.setBounds(120,40,150,25);
		content_pane.add(email);
		
		JLabel twitter_label = new JLabel("twitter: ");
		twitter_label.setBounds(10,70,100, 25);
		content_pane.add(twitter_label);
		
		twitter = new JTextField();
		//nombre_1.setText("Hola Mundo");
		new TextPrompt("twitter", twitter);
		twitter.setBounds(120,70,150,25);
		content_pane.add(twitter);
		
		guardar = new JButton("Guardar");
		guardar.setToolTipText("Guardar Contacto");
		guardar.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		guardar.setBorder(BorderFactory.createEmptyBorder());
		guardar.setBounds(200,100,70,25);
		content_pane.add(guardar);
		
		cancelar = new JButton("Cancelar");
		cancelar.setToolTipText("Omitir Contacto");
		cancelar.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		cancelar.setBorder(BorderFactory.createEmptyBorder());
		cancelar.setBounds(120,100,70,25);
		content_pane.add(cancelar);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == guardar){
			String str_telefono = telefono.getText();
			String str_email = email.getText();
			String str_twitter = twitter.getText();
			
			try{
				Integer int_telefono = Integer.parseInt(str_telefono);
			}catch (Exception ex){
				JOptionPane.showMessageDialog(this, "Error en la escritura de telefono");
				return;
			}
			
			
		   try {
			   InternetAddress emailAddr = new InternetAddress(str_email);
			   emailAddr.validate();
		   } catch (Exception ex) {
			   JOptionPane.showMessageDialog(this, "Error en la escritura de e-mail");
			   return;
		   }
			
			System.out.println("1 "+str_telefono+" 2 "+str_email+" 3 "+str_twitter);
		}else if (e.getSource() == cancelar){
			//System.out.println("cancelar");
			this.dispose();
			//this.setVisible(false);
		}
		
	}
	
	public String prueba(){
		return "hola";
	}

}
