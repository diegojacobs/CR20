package Twitter;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.ResponseList;

/**
@author Diego Jacobs
Date: May 11, 2016
 */
public class MyTwitter {
	private TwitterFactory factory;
	private Twitter twitter;
	
	public MyTwitter()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	       .setOAuthConsumerKey("JwdCyEN3QoO3MoAzqVpjItzTa")
	       .setOAuthConsumerSecret(	"I8BPQROjPq254dxBHzesDVlUZiOVtKEO1aLcVG1xBAelZdo8JK")
	       .setOAuthAccessToken("69966536-bxn2YRwfhunK0CmyyPcu4Y5VcH9gJldC6k0cU2f7L")
	       .setOAuthAccessTokenSecret("tjaE9qdjxihwSAt55huJUXBtahGAj3WRQkH2soLK0Fn9i");
	    
	    factory = new TwitterFactory(cb.build());
        twitter = factory.getInstance();
	}
	
	public User getUser(String user)
	{
		String[] usuarios = {user};
		ResponseList<User> lista;
		try 
		{
			lista = twitter.lookupUsers(usuarios);
			
			return lista.get(0);
		} 
		catch (TwitterException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return null;
	}
	
	public List<Status> getUserTimeLine(User user, int page, int count)
	{
		try 
		{
			return twitter.getUserTimeline(user.getId(), new Paging(page, count));
		} 
		catch (TwitterException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
