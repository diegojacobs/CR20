package classes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import connectionDB.myConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LocationGUI extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JTextField txtCiudad;
	private JTextField txtPais;
	private JTextField txtDireccion;
	private JFormattedTextField txtZipCode;
	
	private Location location;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocationGUI frame = new LocationGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * OnClick Guardar
	 */
	private void guardar() {
		
		
			String str_zipCode = txtZipCode.getText();
			try
			{
				int zipCode = Integer.parseInt(str_zipCode);
				if ( (zipCode >= 0) && (str_zipCode.length() == 5) )
				{
					myConnection connection = new myConnection("postgres","root");
					String insertStatus;
					   if (location != null){
						   location.setCiudad(txtCiudad.getText());
						   location.setPais(txtPais.getText());
						   location.setCodigoPostal(zipCode);
						   location.setDireccion(txtDireccion.getText());
						   insertStatus = location.updateLocation();
					   }else{
						   location = new Location(txtCiudad.getText(), txtPais.getText(), zipCode, txtDireccion.getText(), connection);
						   insertStatus = location.insertLocation();
					   }
					if (insertStatus != null)
						System.out.println(insertStatus);
						//JOptionPane.showMessageDialog(null, "Error en el ZipCode", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
					else
					{
						JOptionPane.showMessageDialog(this, "Location guardada exitosamente", "Location", JOptionPane.PLAIN_MESSAGE);
						this.dispose();
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "Error en el ZipCode", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			catch (NumberFormatException ee)
			{
				JOptionPane.showMessageDialog(this, "Error en el ZipCode", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		
	}
	
	/**
	 * OnClick Cancelar
	 */
	private void cancelar() {
		
		this.dispose();
	}
	
	public LocationGUI(JFrame frame){
		super(frame, "Direccion", true);
		initialize();
	}
	
	public LocationGUI(JDialog frame){
		super(frame, "Direccion", true);
		initialize();
	}
	
	public LocationGUI(JFrame frame, Location location){
		super(frame, "Direccion", true);
		initialize();
		setValues(location);
	}
	
	public LocationGUI(JDialog frame, Location location){
		super(frame, "Direccion", true);
		initialize();
		setValues(location);
	}
	
	public void setValues(Location location){
		txtCiudad.setText(location.getCiudad());
		txtPais.setText(location.getPais());;
		txtDireccion.setText(location.getDireccion());
		txtZipCode.setText(""+location.getCodigoPostal());;
	}
	
	/**
	 * Create the frame.
	 */
	public void  initialize() {
		//setTitle("Location");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(450,300));
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCiudad.setBounds(12, 13, 61, 22);
		contentPane.add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setBounds(106, 15, 314, 22);
		contentPane.add(txtCiudad);
		txtCiudad.setColumns(10);
		
		JLabel lblPais = new JLabel("Pa\u00EDs");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPais.setBounds(12, 48, 61, 22);
		contentPane.add(lblPais);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(106, 50, 314, 22);
		contentPane.add(txtPais);
		
		JLabel lblZipCode = new JLabel("Zip Code");
		lblZipCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblZipCode.setBounds(12, 83, 82, 22);
		contentPane.add(lblZipCode);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDireccin.setBounds(12, 118, 92, 22);
		contentPane.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(106, 120, 314, 22);
		contentPane.add(txtDireccion);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setActionCommand("boton_guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.setBounds(51, 181, 144, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setActionCommand("boton_cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelar.setBounds(242, 181, 144, 29);
		contentPane.add(btnCancelar);
		
		Image img_save;
		Image img_cancel;
		try
		{
			img_save = ImageIO.read(getClass().getResource("/images/save.png"));
			img_cancel = ImageIO.read(getClass().getResource("/images/cancel-icon.png"));
			btnGuardar.setIcon(new ImageIcon(img_save));
			btnCancelar.setIcon(new ImageIcon(img_cancel));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		txtZipCode = new JFormattedTextField();
		txtZipCode.setBounds(106, 85, 89, 22);
		contentPane.add(txtZipCode);
		/*
		// Aceptar solo numeros positivos en ZipCode
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	    //formatter.setAllowsInvalid(false);
	    		
	    txtZipCode = new JFormattedTextField(formatter);
		txtZipCode.setBounds(106, 85, 89, 22);
		contentPane.add(txtZipCode);
		*/
		
		this.pack();
		
	}
	
	
	public Object showDialog(){
		this.setVisible(true);
		return location;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("boton_guardar")){
			guardar();
		}else if(e.getActionCommand().equals("boton_cancelar")){
			cancelar();
		}
		
	}
}
