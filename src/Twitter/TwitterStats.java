package Twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.User;
import twitter4j.UserMentionEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;

/**
@author Diego Jacobs
Date: May 11, 2016
 */
public class TwitterStats {
	private MyTwitter twitter;
	private MongoClient mc;
	private MongoDatabase db;
	private MongoCollection<Document> collection;
	private Gson gson;
	private ArrayList<Tweet> timeline;
	private TwitterUser tuitero;
	private User usuario;
	
	public TwitterStats()
	{
		this.twitter = new MyTwitter();
		this.mc = new MongoClient();
		this.db = this.mc.getDatabase("twitterDB");
		this.collection = this.db.getCollection("Users");
		this.gson = new GsonBuilder().setPrettyPrinting().create();	
	}
	
	public void insertUser(String user)
	{
		this.usuario = twitter.getUser(user);
		List<Status> tweets = twitter.getUserTimeLine(usuario, 1, 200);
		
		this.timeline = new ArrayList<Tweet>();
		for (Status tweet : tweets)
		{
			Tweet mtweet = new Tweet(tweet.getCreatedAt(),tweet.getText(),tweet.getUserMentionEntities(),tweet.getRetweetCount(), tweet.getFavoriteCount(), tweet.getPlace(), tweet.getHashtagEntities());
			this.timeline.add(mtweet);			
		}
		
		this.tuitero = new TwitterUser(usuario.getId(),usuario.getScreenName(),usuario.getLocation(),timeline,usuario.getCreatedAt(),usuario.getDescription());
		
		final String record = this.gson.toJson(tuitero);
		Document doc = new Document("User", usuario.getScreenName());
		doc.append("TimeLine", record);
		this.collection.insertOne(doc);
	}
	
	public boolean validateUser(String user)
	{
		if (twitter.getUser(user) == null)
			return false;
		else
			return true;
	
	}
	
	public void getUser(String user)
	{
		Document doc = this.collection.find(eq("User", user)).first();
		TwitterUser tuitero = this.gson.fromJson(doc.getString("TimeLine"), TwitterUser.class);
		this.tuitero = tuitero;
	}
	
	public HashMap<String, String> Hashtags()
	{
		HashMap<String, String> marc = new HashMap();
		for (Tweet tweet : this.tuitero.getTimeLine())
		{
			for (HashtagEntity hashtag : tweet.getHashtags())
			{
				if (marc.containsKey(hashtag.getText()))
				{
					int cont = Integer.valueOf(marc.get(hashtag.getText()));
					cont++;
					marc.put(hashtag.getText(), String.valueOf(cont));
				}
				else
				{
					marc.put(hashtag.getText(), "1");
				}
			}
		}
		
		return marc;
	}
	
	public HashMap<String, String> Mentions()
	{
		HashMap<String, String> marc = new HashMap();
		for (Tweet tweet : this.tuitero.getTimeLine())
		{
			for (UserMentionEntity mention : tweet.getMentions())
			{
				if (marc.containsKey(mention.getScreenName()))
				{
					int cont = Integer.valueOf(marc.get(mention.getScreenName()));
					cont++;
					marc.put(mention.getScreenName(), String.valueOf(cont));
				}
				else
				{
					marc.put(mention.getScreenName(), "1");
				}
			}
		}
		
		return marc;
	}
	
	
	
	public ArrayList<Tweet> getTimeline() {
		return timeline;
	}

	public void setTimeline(ArrayList<Tweet> timeline) {
		this.timeline = timeline;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
}
