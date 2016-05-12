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
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import sun.util.calendar.JulianCalendar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JCalendar;

import connectionDB.myConnection;

import java.sql.Connection;
import java.sql.Date;

import javax.swing.JComboBox;

public class VentaGUI extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JTextField txtTotal;
	private JCalendar calendar;
	private JComboBox<String> comboBoxPago;
	private JComboBox<String> comboBoxCliente;
	private myConnection connection;
	private ArrayList<Pago> pagos;
	private ArrayList<Cliente> clientes;
	
	Venta venta;

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
		
		// TODO Auto-generated method stub
		// Validar ingresos
		String str_total = txtTotal.getText();
		
		int pago = comboBoxPago.getSelectedIndex();
		int cliente = comboBoxCliente.getSelectedIndex();
		double total = 0.0;
		
		int errs = 0;		
		
		if (pago == -1)
		{
			JOptionPane.showMessageDialog(this, "No ha seleccionado un pago", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
			errs++;
		}		
		
		if (cliente == -1)
		{
			JOptionPane.showMessageDialog(this, "No ha seleccionado un cliente", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
			errs++;
		}		
		
		try
		{
			total = Double.parseDouble(str_total);				
		}
		catch (NumberFormatException ee)
		{
			JOptionPane.showMessageDialog(this, "Error en el total", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
			errs++;
		}
		
		if (total < 0)
		{
			JOptionPane.showMessageDialog(this, "Error en el total", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
			errs++;
		}
		
		if (errs == 0)
		{
			connection = new myConnection("postgres","root");
			Date sql_dia = new Date(calendar.getDate().getTime());
			venta = new Venta(pagos.get(pago).getId(), clientes.get(cliente).getId(), total, sql_dia, connection);
			String insertStatus = venta.insertVenta();
			
			if (insertStatus != null)
				System.out.println(insertStatus);
			else
			{
				JOptionPane.showMessageDialog(null, "Venta guardada exitosamente", "Venta", JOptionPane.PLAIN_MESSAGE);
				this.dispose();
			}
		}			
	}
	
	
	/**
	 * OnClick Cancelar
	 */
	private void cancelar(){
		this.dispose();
	}
	
	
	public VentaGUI(JFrame frame){
		super(frame, "Venta", true);
		initialize();
	}
	
	public VentaGUI(JDialog frame){
		super(frame, "Venta", true);
		initialize();
	}
	
	public VentaGUI(JFrame frame, Venta contact){
		super(frame, "Venta", true);
		initialize();
		venta = contact;
		setValues(contact);
	}
	
	public VentaGUI(JDialog frame, Venta contact){
		super(frame, "Venta", true);
		initialize();
		venta = contact;
		setValues(contact);
	}
	
	public void setValues(Venta venta){
		
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		this.setPreferredSize(new Dimension(643, 321));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setBounds(100,100,342,158);
		this.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		//setTitle("Venta");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 643, 321);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		//contentPane.setLayout(null);
		
		JLabel lblPago = new JLabel("Tipo de pago");
		lblPago.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPago.setBounds(12, 55, 106, 22);
		contentPane.add(lblPago);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCliente.setBounds(12, 89, 82, 22);
		contentPane.add(lblCliente);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotal.setBounds(12, 124, 92, 22);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(130, 126, 89, 22);
		contentPane.add(txtTotal);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(this);
		btnGuardar.setActionCommand("boton_guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.setBounds(119, 231, 144, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand("boton_cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelar.setBounds(355, 231, 144, 29);
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
		
		calendar = new JCalendar();
		calendar.setBounds(393, 48, 198, 155);
		contentPane.add(calendar);
		
		JLabel lblDia = new JLabel("D\u00EDa");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDia.setBounds(477, 13, 40, 22);
		contentPane.add(lblDia);
		
		comboBoxPago = new JComboBox<String>();
		comboBoxPago.setBounds(130, 55, 176, 22);		
		connection = new myConnection("postgres","root");
		
		Pago data_pagos = new Pago();
		data_pagos.setCon(connection.getCon());
		String getAllPagos_status = data_pagos.selectAllPagos();
		if (getAllPagos_status != null)
			System.out.println(getAllPagos_status);
		else
		{
			pagos = data_pagos.getAll();
			for (Pago i: pagos)
				comboBoxPago.insertItemAt(i.getDescripcion(), comboBoxPago.getItemCount());
		}
		if (comboBoxPago.getItemCount() > 0)
			comboBoxPago.setSelectedIndex(0);		
		contentPane.add(comboBoxPago);
		
		comboBoxCliente = new JComboBox<String>();
		comboBoxCliente.setBounds(130, 89, 176, 22);
		connection = new myConnection("postgres","root");
		
		Cliente data_clientes = new Cliente();
		data_clientes.setCon(connection.getCon());
		String getAllClientes_status = data_clientes.selectAllClientes();
		if (getAllClientes_status != null)
			System.out.println(getAllClientes_status);
		else
		{
			clientes = data_clientes.getAll();
			for (Cliente i: clientes)
				comboBoxCliente.insertItemAt(i.getNombreCompleto(), comboBoxCliente.getItemCount());
		}
		if (comboBoxCliente.getItemCount() > 0)
			comboBoxCliente.setSelectedIndex(0);
		
		contentPane.add(comboBoxCliente);
		
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("boton_guardar")){
			guardar();
		}else if (e.getActionCommand().equals("boton_cancelar")){
			cancelar();
		}
		
	}
	
	public Object showDialog(){
		this.setVisible(true);
		return venta;
	}
}
