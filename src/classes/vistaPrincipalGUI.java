package classes;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class vistaPrincipalGUI extends JFrame implements ActionListener {
	
	ArrayList<JMenuItem> nuevoItems = new ArrayList();
	ArrayList<JMenuItem> vistaItems = new ArrayList();
	String[] tablas = {"cliente","contacto","estado","estado_civil","location","pago","venta"};
	String[] nombres = {"Cliente","Contacto","Estado","Estado Civil","Location","Pago","Venta"};
	
	public vistaPrincipalGUI(){
		super("La Mera Betty");
		initialize();
	}
	
	public void initialize(){
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.setBounds(100, 100, (width/2), (height/2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Nuevo");
		menuBar.add(mnNewMenu);
		for (int i = 0; i < tablas.length; i++){
			JMenuItem item = new JMenuItem(nombres[i]);
			item.setActionCommand("nuevo_"+tablas[i]);
			item.addActionListener(this);
			nuevoItems.add(item);
			mnNewMenu.add(item);
		}
		
		JMenu mnViewMenu = new JMenu("Ver Tabla");
		menuBar.add(mnViewMenu);
		for (int i = 0; i < tablas.length; i++){
			JMenuItem item = new JMenuItem(nombres[i]);
			item.setActionCommand("ver_"+tablas[i]);
			item.addActionListener(this);
			vistaItems.add(item);
			mnViewMenu.add(item);
		}
		
		
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tablas.length; i++){
			if (e.getActionCommand().equals("nuevo_"+tablas[i])){
				nuevaVentana(tablas[i]);
			}
		}
		
		for (int i = 0; i < tablas.length; i++){
			if (e.getActionCommand().equals("ver_"+tablas[i])){
				verTabla(tablas[i]);
			}
		}
		
	}
	
	public void verTabla(String nombre){
		new vistaDeTabla(nombre).setVisible(true);
	}
	
	public void nuevaVentana(String nombre){
		switch(nombre){
		case "cliente":
			clienteGUI gui = new clienteGUI(this);
			gui.showDialog();
			break;
		case "contacto":
			contactGUI gui2 = new contactGUI(this);
			gui2.showDialog();
			break;
		case "estado":
			estadoGUI gui3 = new estadoGUI(this);
			gui3.showDialog();
			break;
		case "estado_civil":
			estadoCivilGUI gui4 = new estadoCivilGUI(this);
			gui4.showDialog();
			break;
		case "location":
			LocationGUI gui5 = new LocationGUI(this);
			gui5.showDialog();
			break;
		case "pago":
			PagoGUI gui6 = new PagoGUI(this);
			gui6.showDialog();
			break;
		case "venta":
			VentaGUI gui7 = new VentaGUI(this);
			gui7.showDialog();
			break;
		}
	}
}
