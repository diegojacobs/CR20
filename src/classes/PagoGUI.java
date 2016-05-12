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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import connectionDB.myConnection;

public class PagoGUI extends JDialog implements ActionListener {

	private JPanel contentPane;
	private Pago pago;

	public PagoGUI(JFrame frame){
		super(frame, "Pago", true);
		initialize();
	}
	
	public PagoGUI(JDialog frame){
		super(frame, "Pago", true);
		initialize();
	}
	
	public PagoGUI(JFrame frame, Pago contact){
		super(frame, "Pago", true);
		initialize();
		pago = contact;
		setValues(contact);
	}
	
	public PagoGUI(JDialog frame, Pago contact){
		super(frame, "Pago", true);
		initialize();
		pago = contact;
		setValues(contact);
	}
	
	public void setValues(Pago contact){
		txtDesc.setText(contact.getDescripcion());
	}
	/**
	 * OnClick Guardar
	 */
	private void guardar() {
		
		
			// TODO Auto-generated method stub
		if (txtDesc.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Descripcion es un campo obligatorio");
			return;
		}

			myConnection connection = new myConnection("postgres","root");
			
			String insertStatus;
			   if (pago != null){
				   pago.setDescripcion(txtDesc.getText());
				   insertStatus = pago.updatePago();
			   }else{
				   pago = new Pago(txtDesc.getText(), connection.getCon());
				   insertStatus = pago.insertPago();
			   }
			if (insertStatus != null)
				System.out.println(insertStatus);
			else
			{
				JOptionPane.showMessageDialog(this, "Pago guardado exitosamente", "Pago", JOptionPane.PLAIN_MESSAGE);
				this.dispose();
			}
		
	}
	
	/**
	 * OnClick Cancelar
	 */
	private void cancelar() {
		
		this.dispose();
	}
	private JTextField txtDesc;
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		//setTitle("Pago");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setPreferredSize(new Dimension(342,158));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setBounds(100,100,342,158);
		this.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCiudad = new JLabel("Tipo de Pago");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCiudad.setBounds(12, 13, 107, 22);
		contentPane.add(lblCiudad);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(this);
		btnGuardar.setActionCommand("boton_guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.setBounds(12, 70, 144, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand("boton_cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelar.setBounds(168, 70, 144, 29);
		contentPane.add(btnCancelar);
		
		Image img_save;
		Image img_cancel;
		try
		{
			img_save = ImageIO.read(getClass().getResource("/images/save.png"));
			img_cancel = ImageIO.read(getClass().getResource("/images/cancel-icon.png"));
			btnGuardar.setIcon(new ImageIcon(img_save));
			btnCancelar.setIcon(new ImageIcon(img_cancel));
			
			txtDesc = new JTextField();
			txtDesc.setBounds(149, 15, 163, 22);
			contentPane.add(txtDesc);
			txtDesc.setColumns(10);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.pack();
			
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
	
	public Object showDialog(){
		this.setVisible(true);
		return pago;
	}
}
