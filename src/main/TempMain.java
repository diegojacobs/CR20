package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		/*
		//LocationGUI frame = new LocationGUI();
		//PagoGUI frame = new PagoGUI();
		VentaGUI frame = new VentaGUI();
		frame.setVisible(true);
		
		
		
		//Connection to DataBase
		myConnection connection = new myConnection("postgres","root");
		Contacto contact = new Contacto(30526044,"diego@gmail.com","diego",connection);
	
		Venta sale = new Venta(1, 1, 1, 10, 12.5, new Date(2015, 12, 30), connection);
		
		String text = contact.insertContacto();
		
		if (text == null)
			System.out.println("Exito");
		else
			System.out.println(text);
		//contactDB.selectContacto();
		//contactDB.selectAllContactos();
		//contactDB.deleteContacto();
		//contactDB.updateContacto();
		*/
		
		TwitterStats tw = new TwitterStats();
		tw.insertUser("el_angelm");
		
		for (Tweet tweet: tw.getTimeline())
		{
			System.out.println("Tweet: " + tweet.getTweet() + " Fecha:" + tweet.getFecha().toString());
		}
	    
	}
}
