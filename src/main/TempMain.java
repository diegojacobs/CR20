package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import classes.ChartsTwitter;
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

		//Charts frame = new Charts();
		//frame.setVisible(true);
		
		
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
		else
		{
			JOptionPane.showMessageDialog(null, "El usuario de Twitter no existe", "Error en el ingreso de datos", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
