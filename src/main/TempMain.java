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
		Charts frame = new Charts();
		frame.setVisible(true);*//*
>>>>>>> refs/remotes/origin/master
		
		
		//Connection to DataBase
		myConnection connection = new myConnection("postgres","root");
		Contacto contact = new Contacto(30526044,"diego@gmail.com","diego",connection);
	
		Venta sale = new Venta(1, 1, 10, 12.5, new Date(2015, 12, 30), connection);
		
		String text = contact.insertContacto();
		
		if (text == null)
			System.out.println("Exito");
		else
			System.out.println(text);
		//contactDB.selectContacto();
		//contactDB.selectAllContactos();
		//contactDB.deleteContacto();
		//contactDB.updateContacto();
		
		TwitterStats tw = new TwitterStats();
		tw.insertUser("el_angelm");
		
		for (Tweet tweet: tw.getTimeline())
		{
			System.out.println("Tweet: " + tweet.getTweet() + " Fecha:" + tweet.getFecha().toString());
		}

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

