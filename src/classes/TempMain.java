package classes;

import java.util.List;

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
	        	if (status.getUser().getScreenName().equals("el_angelm"))
	        		System.out.println("Showing @"+status.getUser().getScreenName()+" -> " +status.getText());
	        }
	          
	    }
	    catch(TwitterException e)
	    {
	    	
	    }
	}
}
