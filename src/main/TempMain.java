package main;

import classes.Charts;
import classes.vistaPrincipalGUI;

/**
@author Diego Jacobs
Date: May 9, 2016
 */
public class TempMain {

	public static void main(String[] args) {
		
		Charts frame = new Charts();
		frame.setVisible(true);
	}
}
		//new VentaGUI((JFrame)null).showDialog();
		/*Contacto contacto = new Contacto(123456,"algo@algo","asdfghjkl");
		//contactGUI gui = new contactGUI((JFrame)null,contacto);
		
		
		Cliente cliente = new Cliente();
		cliente.setId(1002);
		myConnection connection = new myConnection("postgres","root");
		cliente.setCon(connection.getCon());
		System.out.println(cliente.selectCliente());
		
		clienteGUI pago = new clienteGUI((JFrame)null,cliente);
		
		//estadoGUI pago = new estadoGUI((JFrame)null);
		pago.showDialog();
		//new vistaDeTabla().setVisible(true);;
		
		//gui.showDialog();
		
		/*
		//LocationGUI frame = new LocationGUI();
		//PagoGUI frame = new PagoGUI();
		clienteGUI frame = new clienteGUI((JDialog)null);
		Object obj = frame.showDialog();
		//System.out.println(obj);
		
=======

		
		//LocationGUI frame = new LocationGUI();
		//PagoGUI frame = new PagoGUI();
		//VentaGUI frame = new VentaGUI();
<<<<<<< HEAD

		//Charts frame = new Charts();
		//frame.setVisible(true);
=======
		Charts frame = new Charts();
		frame.setVisible(true);*//*
>>>>>>> refs/remotes/origin/master
>>>>>>> refs/remotes/origin/master
		
		
		//Connection to Twitter		    
		TwitterStats tw = new TwitterStats();
		
		if (tw.validateUser("diegojacobs95"))
		{
			tw.insertUser("diegojacobs95");
			
			for (Tweet tweet: tw.getTimeline())
			{
				System.out.println("Tweet: " + tweet.getTweet() + " Fecha:" + tweet.getFecha().toString());
			}
			
			ChartsTwitter frame = new ChartsTwitter(tw.Hashtags(), tw.Mentions());
			frame.setVisible(true);
		}
<<<<<<< HEAD
		else
		{
			JOptionPane.showMessageDialog(null, "El usuario de Twitter no existe", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
=======

		//Traer tweets
		/*ConfigurationBuilder cb = new ConfigurationBuilder();
	       cb.setDebugEnabled(true)
	           .setOAuthConsumerKey("JwdCyEN3QoO3MoAzqVpjItzTa")
	           .setOAuthConsumerSecret(	"I8BPQROjPq254dxBHzesDVlUZiOVtKEO1aLcVG1xBAelZdo8JK")
	           .setOAuthAccessToken("69966536-bxn2YRwfhunK0CmyyPcu4Y5VcH9gJldC6k0cU2f7L")
	           .setOAuthAccessTokenSecret("tjaE9qdjxihwSAt55huJUXBtahGAj3WRQkH2soLK0Fn9i");

	    try 
	    {
	    	TwitterFactory factory = new TwitterFactory(cb.build());
	        Twitter twitter = factory.getInstance();
	        User user = twitter.verifyCredentials();
	        List<Status> statusess = twitter.getHomeTimeline();
	        
	        for(Status status : statusess)
	        {
	        	//if (status.getUser().getScreenName().equals("el_angelm"))
	        		System.out.println("Showing @"+status.getUser().getScreenName()+" -> " +status.getText());

	    
	}*/
