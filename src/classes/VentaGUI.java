package classes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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

import sun.util.calendar.JulianCalendar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;

import java.sql.Date;

public class VentaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtPago;
	private JTextField txtCliente;
	private JTextField txtTotal;
	private JFormattedTextField txtCantidad;
	private JCalendar calendar;

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
	private ActionListener guardar = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// Validar ingresos			
			String str_pago = txtPago.getText();
			String str_cliente = txtCliente.getText();
			String str_cantidad = txtCantidad.getText();
			String str_total = txtTotal.getText();
			
			int pago = 0;
			int cliente = 0;
			int cantidad = 0;
			double total = 0.0;
			
			int errs = 0;
			
			try
			{
				pago = Integer.parseInt(str_pago);				
			}
			catch (NumberFormatException ee)
			{
				JOptionPane.showMessageDialog(null, "Error en el # de pago", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
			}
			
			if (pago < 0)
			{
				JOptionPane.showMessageDialog(null, "Error en el # de pago", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
				errs++;
			}
			
			try
			{
				cliente = Integer.parseInt(str_cliente);				
			}
			catch (NumberFormatException ee)
			{
				JOptionPane.showMessageDialog(null, "Error en el # de cliente", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
			}
			
			if (cliente < 0)
			{
				JOptionPane.showMessageDialog(null, "Error en el # de cliente", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
				errs++;
			}
			
			try
			{
				cantidad = Integer.parseInt(str_cantidad);				
			}
			catch (NumberFormatException ee)
			{
				JOptionPane.showMessageDialog(null, "Error en la cantidad", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
			}
			
			if (cantidad < 0)
			{
				JOptionPane.showMessageDialog(null, "Error en la cantidad", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
				errs++;
			}
			
			try
			{
				total = Double.parseDouble(str_total);				
			}
			catch (NumberFormatException ee)
			{
				JOptionPane.showMessageDialog(null, "Error en el total", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
			}
			
			if (total < 0)
			{
				JOptionPane.showMessageDialog(null, "Error en el total", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
				errs++;
			}
			
			if (errs == 0)
			{
				Date sql_dia = new Date(calendar.getDate().getTime());
				Venta venta_user = new Venta(pago, cliente, cantidad, total, sql_dia);
				String insertStatus = venta_user.insertVenta();
				if (insertStatus != null)
					System.out.println(insertStatus);
				else
					JOptionPane.showMessageDialog(null, "Venta guardada exitosamente", "Venta", JOptionPane.PLAIN_MESSAGE);
			}			
		}
	};
	
	/**
	 * OnClick Cancelar
	 */
	private ActionListener cancelar = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// Limpiar txts
			txtPago.setText("");
			txtTotal.setText("");
			txtCliente.setText("");
			txtCantidad.setText("");
			txtPago.requestFocus();
		}
	};
	
	/**
	 * Create the frame.
	 */
	public VentaGUI() {
		setTitle("Venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPago = new JLabel("# Pago");
		lblPago.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPago.setBounds(12, 55, 61, 22);
		contentPane.add(lblPago);
		
		txtPago = new JTextField();
		txtPago.setBounds(106, 56, 89, 22);
		contentPane.add(txtPago);
		txtPago.setColumns(10);
		
		JLabel lblCliente = new JLabel("# Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCliente.setBounds(12, 89, 82, 22);
		contentPane.add(lblCliente);
		
		txtCliente = new JTextField();
		txtCliente.setColumns(10);
		txtCliente.setBounds(106, 91, 89, 22);
		contentPane.add(txtCliente);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCantidad.setBounds(12, 124, 82, 22);
		contentPane.add(lblCantidad);
		
		
		txtCantidad = new JFormattedTextField();
		txtCantidad.setBounds(106, 126, 89, 22);
		contentPane.add(txtCantidad);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotal.setBounds(12, 159, 92, 22);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(106, 161, 89, 22);
		contentPane.add(txtTotal);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(guardar);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.setBounds(51, 216, 144, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(cancelar);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelar.setBounds(242, 216, 144, 29);
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
		calendar.setBounds(222, 48, 198, 155);
		contentPane.add(calendar);
		
		JLabel lblDia = new JLabel("D\u00EDa");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDia.setBounds(306, 13, 40, 22);
		contentPane.add(lblDia);
		
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
		
	}
}
