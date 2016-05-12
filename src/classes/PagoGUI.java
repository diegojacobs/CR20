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

public class PagoGUI extends JFrame {

	private JPanel contentPane;

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

			myConnection connection = new myConnection("postgres","root");
			Pago pago_user = new Pago(txtDesc.getText(), connection.getCon());
			String insertStatus = pago_user.insertPago();

			if (insertStatus != null)
				System.out.println(insertStatus);
			else
			{
				JOptionPane.showMessageDialog(null, "Pago guardado exitosamente", "Pago", JOptionPane.PLAIN_MESSAGE);
				txtDesc.setText("");
				txtDesc.requestFocus();
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
			txtDesc.setText("");
			txtDesc.requestFocus();
		}
	};
	private JTextField txtDesc;
	
	/**
	 * Create the frame.
	 */
	public PagoGUI() {
		setTitle("Pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCiudad = new JLabel("Tipo de Pago");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCiudad.setBounds(12, 13, 107, 22);
		contentPane.add(lblCiudad);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(guardar);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.setBounds(12, 70, 144, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(cancelar);
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
			
	}
}
