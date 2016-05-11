package classes;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.mail.internet.InternetAddress;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultStyledDocument;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class clienteGUI extends JFrame implements ActionListener {
	
	JTextField nombre_1, nombre_2, apellido_1, apellido_2, descuento, contacto_informacion, location_informacion;
	JPanel content_pane;
	JDateChooser fecha_nacimiento;
	Choice location, estado, estado_civil, genero;
	JButton foto, guardar, cancelar, contacto_button, location_button;
	ImageIcon foto_imagen;
	ArrayList<Object> contactos;
	ArrayList<Object> locations;
	
	File file;
	JFileChooser fc;
	
	public clienteGUI(){
		super("Cliente");
		initialize();
	}
	
	private void initialize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.setBounds(100, 100,730,360);// (width/2), (height/2));
		//this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fc = new JFileChooser();
		content_pane = new JPanel();
		//content_pane.setBounds(0,0, 400, 400);
		
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
		
		
		foto = new JButton();
		foto.setToolTipText("Seleccione Foto");
		foto.addActionListener(this);
		try{
			Path source = Paths.get("resources/default_profile_picture.jpg"); //store location
			BufferedImage before = ImageIO.read(source.toFile());
			
			int w = before.getWidth();
			int h = before.getHeight();
			int wn = 0;
			int hn = 0;
			
			float scale = 0;
			if ((130/Float.valueOf(w)) > (150/Float.valueOf(h))){
				scale = 130/Float.valueOf(w);
				
			}else{
				scale = 150/Float.valueOf(h);
			}
			
			Double f1 = Double.valueOf(w*scale);
			wn = Integer.valueOf(f1.intValue());
			f1 = Double.valueOf(h*scale);
			hn = Integer.valueOf(f1.intValue());
			
			foto.setIcon(new ImageIcon(((new ImageIcon(
		            before).getImage()
		            .getScaledInstance(wn,hn ,
		                    java.awt.Image.SCALE_SMOOTH)))));
			
			//foto.setIcon(new ImageIcon(after));
			foto.setText("");
			Border emptyBorder = BorderFactory.createEmptyBorder();
			foto.setBorder(emptyBorder);
		} catch (Exception e){
			System.out.println("Error in resources/images/2openButton.png");
		}
		foto.setBounds(570,10 , 130, 150);
		content_pane.add(foto);
		
		
		JLabel estado_civil_label = new JLabel("Estado Civil:");
		estado_civil_label.setBounds(10,130,130, 25);
		content_pane.add(estado_civil_label);
		
		estado_civil = new Choice();
		estado_civil.setBounds(150,130 , 130, 25);
		estado_civil.add("Soltero");
		estado_civil.add("Casado");
		content_pane.add(estado_civil);
		
		JLabel contacto_label = new JLabel("Contacto :");
		contacto_label.setBounds(300,70,70, 25);
		content_pane.add(contacto_label);
		
		contacto_button = new JButton("Asignar Contacto");
		contacto_button.setToolTipText("Asignar un contacto al cliente");
		contacto_button.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		contacto_button.setBorder(BorderFactory.createEmptyBorder());
		contacto_button.setBounds(380,70,110,25);
		content_pane.add(contacto_button);
		
		contacto_informacion = new JTextField();
		contacto_informacion.setEditable(false);
		contacto_informacion.setBounds(300,100,190, 85);
		content_pane.add(contacto_informacion);
		
		JLabel descuento_label = new JLabel("Descuento: ");
		descuento_label.setBounds(10,190,130, 25);
		content_pane.add(descuento_label);
		
		descuento = new JTextField();
		//nombre_1.setText("Hola Mundo");
		new TextPrompt("Descuento", descuento);
		descuento.setText("0.0");
		descuento.setBounds(150,190,130, 25);
		content_pane.add(descuento);
		
		JLabel genero_label = new JLabel("Genero:");
		genero_label.setBounds(10,160,130, 25);
		content_pane.add(genero_label);
		
		genero = new Choice();
		genero.setBounds(150,160,130, 25);
		genero.add("Masculino");
		genero.add("Femenino");
		content_pane.add(genero);
		
		JLabel location_label = new JLabel("Direccion :");
		location_label.setBounds(300,190,70, 25);
		content_pane.add(location_label);
		
		location_button = new JButton("Asignar Direccion");
		location_button.setToolTipText("Asignar una direccion al cliente");
		location_button.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		location_button.setBorder(BorderFactory.createEmptyBorder());
		location_button.setBounds(380,190,110,25);
		content_pane.add(location_button);
		
		contacto_informacion = new JTextField();
		contacto_informacion.setEditable(false);
		contacto_informacion.setBounds(300,220,190, 85);
		content_pane.add(contacto_informacion);
		
		
		guardar = new JButton("Guardar");
		guardar.setToolTipText("Guardar Cliente");
		guardar.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		guardar.setBorder(BorderFactory.createEmptyBorder());
		//foto 570,10 , 130, 150
		guardar.setBounds(630,250,70,25);
		content_pane.add(guardar);
		
		cancelar = new JButton("Cancelar");
		cancelar.setToolTipText("Omitir Cliente");
		cancelar.addActionListener(this);
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		cancelar.setBorder(BorderFactory.createEmptyBorder());
		cancelar.setBounds(630,280,70,25);
		content_pane.add(cancelar);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == guardar){
			Date this_date = fecha_nacimiento.getDate();
			if (this_date == null){
				JOptionPane.showMessageDialog(this, "Error en fecha de nacimiento");
				return;
			}
			
			Double descuento_num;
			try{
				descuento_num = Double.parseDouble(descuento.getText());
			}catch (Exception ex){
				JOptionPane.showMessageDialog(this, "Error en descuento");
				return;
			}
			
			String foto_direccion = "resources/default_profile_picture.jpg";
			if (foto_imagen != null){
				Path source = Paths.get("resources/client_profile/"); //store location
				try{
					Files.createDirectories(source);
				}catch (Exception ex){
					JOptionPane.showMessageDialog(this, "Error inesperado al guardar imagen");
					return;
				}
				
				String nombre = "client_no_";
				int num = 0;
				while (new File("resources/client_profile/"+nombre+num+".jpg").isFile()){
					num +=1;
				}
				

				Path target = source.resolve(nombre+num+".jpg");
				
				Image img = foto_imagen.getImage();

				BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = bi.createGraphics();
				g2.drawImage(img, 0, 0, null);
				g2.dispose();
				foto_direccion = "resources/client_profile/"+nombre+num+".jpg";
				File temp = new File(foto_direccion);

				try{					
					ImageIO.write(bi, "jpg", temp);
					System.out.println("nueva imagen creada");
				}catch (Exception ex){
					
				}
			}
			
			
		}else if (e.getSource() == cancelar){
			//System.out.println("cancelar");
			
			System.out.println("Falta descartar todos los campos agregados (location y contact)");
			
			this.dispose();
			//this.setVisible(false);
		}else if (e.getSource() == foto){
			open();
		}
		
	}
	
	public void open(){
		int returnVal = fc.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION){
        	file = fc.getSelectedFile();
        	BufferedImage before;
        	try{
        		before = ImageIO.read(file);
        		
        	} catch (Exception e){
        		JOptionPane.showMessageDialog(this, "Error en archivo de Imagen");
				return;
        	}
        	try{
	        	int w = before.getWidth();
				int h = before.getHeight();
				int wn = 0;
				int hn = 0;
				
				float scale = 0;
				if ((130/Float.valueOf(w)) > (150/Float.valueOf(h))){
					scale = 130/Float.valueOf(w);
					
				}else{
					scale = 150/Float.valueOf(h);
				}
				
				Double f1 = Double.valueOf(w*scale);
				wn = Integer.valueOf(f1.intValue());
				f1 = Double.valueOf(h*scale);
				hn = Integer.valueOf(f1.intValue());
				
				foto_imagen = new ImageIcon(((new ImageIcon(
			            before).getImage()
			            .getScaledInstance(wn,hn ,
			                    java.awt.Image.SCALE_SMOOTH))));
				
				foto.setIcon(foto_imagen);
				
				//foto.setIcon(new ImageIcon(after));
				foto.setText("");
				Border emptyBorder = BorderFactory.createEmptyBorder();
				foto.setBorder(emptyBorder);
        	}catch (Exception e){
        		JOptionPane.showMessageDialog(this, "Error en archivo seleccionado");
				return;
        	}
        
        	//System.out.println(file.getAbsolutePath());
        }
	}
	
	
	

}
