package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Twitter.Tweet;
import Twitter.TwitterStats;
import connectionDB.myConnection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JCheckBox;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;

public class Charts extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panelG1; 
	private JPanel panelG2;
	private JPanel panelG4;
	private JCheckBox chckbxMasculino;
	private JCheckBox chckbxFemenino;
	private JCheckBox chckbxVentas;
	private JCheckBox chckbxClientes;
	private JComboBox<String> comboBoxNPaises;
	private myConnection connection;

	ArrayList<JMenuItem> nuevoItems = new ArrayList();
	ArrayList<JMenuItem> vistaItems = new ArrayList();
	String[] tablas = {"cliente","contacto","estado","estado_civil","location","pago","venta"};
	String[] nombres = {"Cliente","Contacto","Estado","Estado Civil","Location","Pago","Venta"};
	
	
	
	public void initialize(){
		
		/*GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.setBounds(100, 100, (width/2), (height/2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		
		
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
	
	/**
	 * OnClick G1
	 * BarChart Meses vrs Cantidad de Ventas
	 */
	private ActionListener generarG1 = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			String chart_title = "Cantidad de ventas por mes";
			
			connection = new myConnection("postgres","root");
			Venta ventas_data = new Venta();
			ventas_data.setCon(connection.getCon());
			String status = null;
			boolean filtros = chckbxFemenino.isSelected() || chckbxMasculino.isSelected();
			// Chart sin filtros
			if (! filtros)
			{
				status = ventas_data.selectAllVentas();
			}
			else
			{
				if ( (chckbxFemenino.isSelected()) && (chckbxMasculino.isSelected()) )
					JOptionPane.showMessageDialog(null, "Un usario no puede tener ambos géneros", "Error en los filtros", JOptionPane.ERROR_MESSAGE);
				else
				{
					// Femenino
					if (chckbxFemenino.isSelected())
					{
						status = ventas_data.selectAllVentasClientesMujeres();
						chart_title = "Cantidad de ventas por mes para clientes mujeres";
					}
					// Masculino
					else if (chckbxMasculino.isSelected())
					{
						status = ventas_data.selectAllVentasClientesHombres();
						chart_title = "Cantidad de ventas por mes para clientes hombres";
					}
				}					
			}
			
			if (status != null)
				System.out.println(status);
			else
			{
				// Establecer dataset
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				String serie = "Total de ventas";
				for (int i = 1; i < 13; i++)
				{
					int totalVentas = 0;
					for (Venta vi: ventas_data.getAll())
					{
						int vi_mm = vi.getDia().getMonth() + 1;					
						if (i == vi_mm)
							totalVentas++;
					}
					dataset.addValue(totalVentas, serie, Integer.toString(i));
				}
				// Crear chart
				JFreeChart chart = ChartFactory.createBarChart(chart_title, "Meses", "Ventas", dataset, PlotOrientation.VERTICAL, false, true, false);
				//Modificamos el diseño del chart
				CategoryPlot plot = (CategoryPlot) chart.getPlot();
				BarRenderer renderer = (BarRenderer) plot.getRenderer();
				Color blue = new Color(53, 112, 173);
				renderer.setSeriesPaint(0, blue);
				chart.setAntiAlias(true);
				chart.setBackgroundPaint(new Color(214, 217, 223));
				panelG1.removeAll();
				panelG1.repaint();
				panelG1.setLayout(new java.awt.BorderLayout());
				panelG1.add(new ChartPanel(chart));
				panelG1.validate();
			}
		}
	};
	
	/**
	 * OnClick G2
	 * PieChart Top 5 Paises con mayor cantidad de Ventas/Clientes
	 */
	private ActionListener generarG2 = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			String chart_title = "";
			
			connection = new myConnection("postgres","root");
			Location location_data = new Location();
			location_data.setCon(connection.getCon());
			String status = null;
			ArrayList<Location> locations = new ArrayList<Location>();

			if ( (chckbxClientes.isSelected()) && (chckbxVentas.isSelected()) )
				JOptionPane.showMessageDialog(null, "No se puede filtrar por ambos campos", "Error en los filtros", JOptionPane.ERROR_MESSAGE);
			else
			{
				// Clientes
				if (chckbxClientes.isSelected())
				{
					status = location_data.selectPaisesMasClientes();
					chart_title = "Top " + Integer.toString(comboBoxNPaises.getSelectedIndex()+1) + " Paises con mayor cantidad de clienes";
				}
				// Ventas
				else if (chckbxVentas.isSelected())
				{
					status = location_data.selectPaisesMasVentas();
					chart_title = "Top " + Integer.toString(comboBoxNPaises.getSelectedIndex()+1) + " Paises con mayor cantidad de ventas";
				}
			}
			
			if (status != null)
				System.out.println(status);
			else
			{
				locations = location_data.getAll();
				// Establecer dataset
				DefaultPieDataset dataset = new DefaultPieDataset();
				String serie = "Total de ventas";
				if  (  (! locations.isEmpty()) && (comboBoxNPaises.getSelectedIndex()+1 <= locations.size()) )
				{
					for (int i = 0; i < comboBoxNPaises.getSelectedIndex()+1; i++)					
						dataset.insertValue(dataset.getItemCount(), locations.get(i).getPais(), new Double(locations.get(i).getCodigoPostal()));
				}
				// Crear chart
				JFreeChart chart = ChartFactory.createPieChart(chart_title, dataset, true, true, false);				
				chart.setAntiAlias(true);
				chart.setBackgroundPaint(new Color(214, 217, 223));
				panelG2.removeAll();
				panelG2.repaint();
				panelG2.setLayout(new java.awt.BorderLayout());
				panelG2.add(new ChartPanel(chart));
				panelG2.validate();
			}
		}
	};
	
	/**
	 * OnClick G3
	 * BarChart Tipo de Pago vrs Cantidad de Ventas
	 */
	private ActionListener generarG3 = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			String chart_title = "Cantidad de ventas por tipo de pago";
			
			connection = new myConnection("postgres","root");
			Pago pagos_data = new Pago();
			pagos_data.setCon(connection.getCon());
			ArrayList<Pago> pagos = new ArrayList<Pago>();
			String status = null;			
			boolean filtros = chckbxFemenino.isSelected() || chckbxMasculino.isSelected();
			// Chart sin filtros
			if (! filtros)
			{
				status = pagos_data.selectAllTipoPagos();
			}
			else
			{
				if ( (chckbxFemenino.isSelected()) && (chckbxMasculino.isSelected()) )
					JOptionPane.showMessageDialog(null, "Un usario no puede tener ambos géneros", "Error en los filtros", JOptionPane.ERROR_MESSAGE);
				else
				{
					// Femenino
					if (chckbxFemenino.isSelected())
					{
						status = pagos_data.selectAllTipoPagosMujeres();
						chart_title = "Cantidad de ventas por tipo de pago para clientes mujeres";
					}
					// Masculino
					else if (chckbxMasculino.isSelected())
					{
						status = pagos_data.selectAllTipoPagosHombres();
						chart_title = "Cantidad de ventas por tipo de pago para clientes hombres";
					}
				}					
			}
			
			if (status != null)
				System.out.println(status);
			else
			{
				pagos = pagos_data.getAll();
				// Establecer dataset
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				String serie = "Total de ventas";
				for (Pago i: pagos)
					dataset.addValue(i.getId(), serie, i.getDescripcion());				
				// Crear chart
				JFreeChart chart = ChartFactory.createBarChart(chart_title, "Tipos de pago", "Ventas", dataset, PlotOrientation.VERTICAL, false, true, false);
				//Modificamos el diseño del chart
				CategoryPlot plot = (CategoryPlot) chart.getPlot();
				BarRenderer renderer = (BarRenderer) plot.getRenderer();
				Color blue = new Color(53, 112, 173);
				renderer.setSeriesPaint(0, blue);
				chart.setAntiAlias(true);
				chart.setBackgroundPaint(new Color(214, 217, 223));
				panelG1.removeAll();
				panelG1.repaint();
				panelG1.setLayout(new java.awt.BorderLayout());
				panelG1.add(new ChartPanel(chart));
				panelG1.validate();
			}
		}
	};
	
	/**
	 * OnClick G4
	 * BarChart Dia de la semana vrs Cantidad de Tweets
	 */
	private ActionListener generarG4 = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			String chart_title = "Cantidad de Tweets por dia de la semana";
			
			connection = new myConnection("postgres","root");
			Cliente clientes_data = new Cliente();
			clientes_data.setCon(connection.getCon());
			String status = clientes_data.selectAllTwitters();
			ArrayList<Cliente> clientes = clientes_data.getAll();
			
			ArrayList<String> clientesTwitter = new ArrayList<String>();
			for (Cliente i: clientes)
				clientesTwitter.add(i.getPrimerNombre());
						
			if (status != null)
				System.out.println(status);
			else
			{
				// Establecer dataset
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				String serie = "Total de ventas";
				
				//Connection to Twitter		    
	    		TwitterStats tw = new TwitterStats();
	    		clientesTwitter = new ArrayList<String>();
	    		clientesTwitter.add("DanielOrozco_95");
	    		clientesTwitter.add("diegojacobs95");
	    		clientesTwitter.add("el_angelm");
	    		clientesTwitter.add("Ronald_MacKay");
	    		clientesTwitter.add("2010MisterChip");
	    		HashMap<String, Integer> hashTw = tw.days(clientesTwitter);
	    		
	    		for (Map.Entry<String, Integer> entry: hashTw.entrySet())
					dataset.addValue(entry.getValue(), serie, entry.getKey());
	    		
				// Crear chart
				JFreeChart chart = ChartFactory.createBarChart(chart_title, "Dia de la semana", "Tweets", dataset, PlotOrientation.VERTICAL, false, true, false);
				//Modificamos el diseño del chart
				CategoryPlot plot = (CategoryPlot) chart.getPlot();
				BarRenderer renderer = (BarRenderer) plot.getRenderer();
				Color blue = new Color(53, 112, 173);
				renderer.setSeriesPaint(0, blue);
				chart.setAntiAlias(true);
				chart.setBackgroundPaint(new Color(214, 217, 223));
				panelG4.removeAll();
				panelG4.repaint();
				panelG4.setLayout(new java.awt.BorderLayout());
				panelG4.add(new ChartPanel(chart));
				panelG4.validate();
			}
		}
	};

	/**
	 * Create the frame.
	 */
	public Charts() {
		setTitle("CRM20 - UVG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1542, 983);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		initialize();
		
		JPanel parentG1 = new JPanel();
		parentG1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		parentG1.setBounds(42, 32, 702, 440);
		contentPane.add(parentG1);
		parentG1.setLayout(null);
		
		JLabel lblFiltrarPor = new JLabel("Filtrar Cliente");
		lblFiltrarPor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFiltrarPor.setBounds(38, 13, 133, 25);
		parentG1.add(lblFiltrarPor);
		
		chckbxMasculino = new JCheckBox("Masculino");
		chckbxMasculino.setBounds(8, 37, 85, 25);
		parentG1.add(chckbxMasculino);
		
		panelG1 = new JPanel();
		panelG1.setBounds(12, 88, 678, 339);
		panelG1.setBackground(new Color(214, 217, 223));
		parentG1.add(panelG1);
		
		chckbxFemenino = new JCheckBox("Femenino");
		chckbxFemenino.setBounds(104, 37, 85, 25);
		parentG1.add(chckbxFemenino);
		
		JButton btnG1 = new JButton("GENERAR CHART - Ventas vrs Meses");
		btnG1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnG1.addActionListener(generarG1);
		btnG1.setBounds(270, 14, 345, 25);
		parentG1.add(btnG1);
		
		JButton btnG3 = new JButton("GENERAR CHART - Ventas vrs Tipo de pago");
		btnG3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnG3.addActionListener(generarG3);
		btnG3.setBounds(270, 50, 345, 25);
		parentG1.add(btnG3);
		
		JPanel parentG2 = new JPanel();
		parentG2.setLayout(null);
		parentG2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		parentG2.setBounds(776, 32, 702, 440);
		contentPane.add(parentG2);
		
		JLabel lblPaisConMayor = new JLabel("Pais con mayor n\u00FAmero de");
		lblPaisConMayor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPaisConMayor.setBounds(38, 13, 240, 25);
		parentG2.add(lblPaisConMayor);
		
		chckbxVentas = new JCheckBox("Ventas");
		chckbxVentas.setSelected(true);
		chckbxVentas.setBounds(68, 35, 85, 25);
		parentG2.add(chckbxVentas);
		
		panelG2 = new JPanel();
		panelG2.setBackground(new Color(214, 217, 223));
		panelG2.setBounds(12, 88, 678, 339);
		parentG2.add(panelG2);
		
		chckbxClientes = new JCheckBox("Clientes");
		chckbxClientes.setBounds(168, 37, 85, 25);
		parentG2.add(chckbxClientes);
		
		JButton btnG2 = new JButton("GENERAR CHART");
		btnG2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnG2.addActionListener(generarG2);
		btnG2.setBounds(530, 34, 160, 25);
		parentG2.add(btnG2);
		
		JLabel lblNewLabel = new JLabel("# Paises");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(347, 17, 85, 16);
		parentG2.add(lblNewLabel);
		
		comboBoxNPaises = new JComboBox<String>();
		comboBoxNPaises.setBounds(347, 36, 85, 22);
		
		connection = new myConnection("postgres","root");
		Location location_data = new Location();
		location_data.setCon(connection.getCon());
		String status = location_data.selectAllPaises();
		if (status != null)
			System.out.println(status);
		else
		{
			for (int i = 1; i < location_data.getAll().size()+1; i++)
				comboBoxNPaises.insertItemAt(Integer.toString(i), comboBoxNPaises.getItemCount());
			if (comboBoxNPaises.getItemCount() > 0)
				comboBoxNPaises.setSelectedIndex(0);
		}
		
		parentG2.add(comboBoxNPaises);
		
		JPanel parentG4 = new JPanel();
		parentG4.setLayout(null);
		parentG4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		parentG4.setBounds(42, 485, 702, 403);
		contentPane.add(parentG4);
		
		panelG4 = new JPanel();
		panelG4.setBackground(new Color(214, 217, 223));
		panelG4.setBounds(12, 51, 678, 339);
		parentG4.add(panelG4);
		
		JButton btnG4 = new JButton("GENERAR CHART - Tweets por dia de la semana");
		btnG4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnG4.addActionListener(generarG4);
		btnG4.setBounds(173, 13, 366, 25);
		parentG4.add(btnG4);
		
	}
}
