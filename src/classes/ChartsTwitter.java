package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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

import connectionDB.myConnection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

public class ChartsTwitter extends JFrame {

	private JPanel contentPane;
	private JPanel panelG1;
	private JCheckBox chckbxHashtags;
	private JCheckBox chckbxUsersMentioned;
	private JComboBox<String> comboBoxN;
	private myConnection connection;
	
	private HashMap<String, String> mentions = new HashMap<String, String>();
	private HashMap<String, String> hashtags = new HashMap<String, String>();
	private Map<String, String> sortedHashtags;
	private Map<String, String> sortedMentions;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Charts frame = new Charts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	 * OnClick G1
	 * PieChart Top 5 Paises con mayor cantidad de Ventas/Clientes
	 */
	private ActionListener generarG1 = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			String chart_title = "";			

			if ( (chckbxUsersMentioned.isSelected()) && (chckbxHashtags.isSelected()) )
				JOptionPane.showMessageDialog(null, "No se puede filtrar por ambos campos", "Error en los filtros", JOptionPane.ERROR_MESSAGE);
			else
			{
				// Users Mentioned
				if (chckbxUsersMentioned.isSelected())
				{
					// Establecer dataset
					DefaultPieDataset dataset = new DefaultPieDataset();
					String serie = "Total de menciones";
					if (comboBoxN.getSelectedIndex() + 1 < sortedMentions.size())
					{
						int i = 0;
						for (Map.Entry<String, String> entry: sortedMentions.entrySet())
						{
							if (i < comboBoxN.getSelectedIndex() + 1)
								dataset.insertValue(dataset.getItemCount(), entry.getKey(), new Double(entry.getValue()));
							else
								break;
							i++;
						}
					}
					// Crear chart
					JFreeChart chart = ChartFactory.createPieChart(chart_title, dataset, true, true, false);				
					chart.setAntiAlias(true);
					chart.setBackgroundPaint(new Color(214, 217, 223));
					panelG1.removeAll();
					panelG1.repaint();
					panelG1.setLayout(new java.awt.BorderLayout());
					panelG1.add(new ChartPanel(chart));
					panelG1.validate();
					chart_title = "Top " + Integer.toString(comboBoxN.getSelectedIndex()+1) + " usuarios con mayor cantidad de menciones";
				}
				// Hashtags
				else if (chckbxHashtags.isSelected())
				{
					// Establecer dataset
					DefaultPieDataset dataset = new DefaultPieDataset();
					String serie = "Total de hashtags";
					if (comboBoxN.getSelectedIndex() + 1 < sortedHashtags.size())
					{
						int i = 0;
						for (Map.Entry<String, String> entry: sortedHashtags.entrySet())
						{
							if (i < comboBoxN.getSelectedIndex() + 1)
								dataset.insertValue(dataset.getItemCount(), entry.getKey(), new Double(entry.getValue()));
							else
								break;
							i++;
						}
					}
					// Crear chart
					JFreeChart chart = ChartFactory.createPieChart(chart_title, dataset, true, true, false);				
					chart.setAntiAlias(true);
					chart.setBackgroundPaint(new Color(214, 217, 223));
					panelG1.removeAll();
					panelG1.repaint();
					panelG1.setLayout(new java.awt.BorderLayout());
					panelG1.add(new ChartPanel(chart));
					panelG1.validate();
					chart_title = "Top " + Integer.toString(comboBoxN.getSelectedIndex()+1) + " hashtags usados";
				}
			}
		}
	};
	
		/**
	 * Create the frame.
	 */
	public ChartsTwitter(HashMap<String, String> hashtags, HashMap<String, String> mentions) {
		
		this.hashtags = hashtags;
		this.mentions = mentions;
		
		// Hacer sort a los hashmaps
		List<Entry<String, String>> entries = new ArrayList<Entry<String, String>>(hashtags.entrySet());
		Collections.sort(entries, new Comparator<Entry<String, String>>()
		{
		  public int compare(Entry<String, String> left, Entry<String, String> right) {
		    return (Integer.parseInt(right.getValue()) - Integer.parseInt(left.getValue()));
		  }
		});
		sortedHashtags = new LinkedHashMap<String, String>(entries.size());
		for (Entry<String, String> entry : entries) {
			sortedHashtags.put(entry.getKey(), entry.getValue());
		}
		
		List<Entry<String, String>> entries_2 = new ArrayList<Entry<String, String>>(mentions.entrySet());
		Collections.sort(entries_2, new Comparator<Entry<String, String>>()
		{
		  public int compare(Entry<String, String> left, Entry<String, String> right) {
			  return (Integer.parseInt(right.getValue()) - Integer.parseInt(left.getValue()));
		  }
		});
		sortedMentions = new LinkedHashMap<String, String>(entries_2.size());
		for (Entry<String, String> entry : entries_2) {
			sortedMentions.put(entry.getKey(), entry.getValue());
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 744, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel parentG1 = new JPanel();
		parentG1.setLayout(null);
		parentG1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		parentG1.setBounds(12, 13, 702, 440);
		contentPane.add(parentG1);
		
		JLabel lblPaisConMayor = new JLabel("Mayor n\u00FAmero de");
		lblPaisConMayor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPaisConMayor.setBounds(80, 13, 173, 25);
		parentG1.add(lblPaisConMayor);
		
		chckbxHashtags = new JCheckBox("Hashtags");
		chckbxHashtags.setSelected(true);
		chckbxHashtags.setBounds(12, 37, 85, 25);
		parentG1.add(chckbxHashtags);
		
		panelG1 = new JPanel();
		panelG1.setBackground(new Color(214, 217, 223));
		panelG1.setBounds(12, 88, 678, 339);
		parentG1.add(panelG1);
		
		chckbxUsersMentioned = new JCheckBox("Usuarios Mencionados");
		chckbxUsersMentioned.setBounds(125, 37, 173, 25);
		parentG1.add(chckbxUsersMentioned);
		
		JButton btnG1 = new JButton("GENERAR CHART");
		btnG1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnG1.addActionListener(generarG1);
		btnG1.setBounds(530, 34, 160, 25);
		parentG1.add(btnG1);
		
		JLabel lblNewLabel = new JLabel("Cantidad");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(347, 17, 85, 16);
		parentG1.add(lblNewLabel);
		
		comboBoxN = new JComboBox<String>();
		comboBoxN.setBounds(347, 36, 85, 22);
		
		for (int i = 0; i < 10; i++)
			comboBoxN.insertItemAt(Integer.toString(i+1), comboBoxN.getItemCount());
		comboBoxN.setSelectedIndex(0);
		
		parentG1.add(comboBoxN);
		
	}
}
