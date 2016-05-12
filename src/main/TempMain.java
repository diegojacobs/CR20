package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Charts;

import Twitter.Tweet;
import Twitter.TwitterStats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import classes.Contacto;
import classes.LocationGUI;
import classes.PagoGUI;
import classes.Venta;
import classes.VentaGUI;
import connectionDB.myConnection;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
@author Diego Jacobs
Date: May 9, 2016
 */
public class TempMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//LocationGUI frame = new LocationGUI();
		//PagoGUI frame = new PagoGUI();
		//VentaGUI frame = new VentaGUI();
<<<<<<< HEAD
=======
		//Charts frame = new Charts();
>>>>>>> refs/remotes/origin/master
		//frame.setVisible(true);
		
		
		//Connection to DataBase
		myConnection connection = new myConnection("postgres","root");
		Contacto contact = new Contacto(30526044,"dgo@gmail.com","diegoja",connection);
	
<<<<<<< HEAD

=======
		Venta sale = new Venta(1, 1, 10, 12.5, new Date(2015, 12, 30), connection);
		
>>>>>>> refs/remotes/origin/master
		String text = contact.insertContacto();
		
		if (text == null)
			System.out.println("Exito");
		else
			System.out.println(text);
		
		//contactDB.selectContacto();
		//contactDB.selectAllContactos();
		//contactDB.deleteContacto();
		//contactDB.updateContacto();
		
<<<<<<< HEAD
=======
		TwitterStats tw = new TwitterStats();
		tw.insertUser("el_angelm");
		
		for (Tweet tweet: tw.getTimeline())
		{
			System.out.println("Tweet: " + tweet.getTweet() + " Fecha:" + tweet.getFecha().toString());
		}

>>>>>>> refs/remotes/origin/master
		//Traer tweets
		ConfigurationBuilder cb = new ConfigurationBuilder();
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
	        }
	          
	    }
	    catch(TwitterException e)
	    {
	    	
	    }
	    
	    String[] usuarios = {"el_angelm"};
	    
<<<<<<< HEAD
	    */
	    
		TwitterStats tw = new TwitterStats();
		
		if (tw.validateUser("r84y"))
		{
			tw.insertUser("att");
			
			for (Tweet tweet: tw.getTimeline())
			{
				System.out.println("Tweet: " + tweet.getTweet() + " Fecha:" + tweet.getFecha().toString());
			}
		}
=======
>>>>>>> refs/remotes/origin/master
	}
}
