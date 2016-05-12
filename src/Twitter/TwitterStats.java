package Twitter;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import twitter4j.Status;
import twitter4j.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
		
		TwitterUser tuitero = new TwitterUser(usuario.getId(),usuario.getScreenName(),usuario.getLocation(),timeline,usuario.getCreatedAt(),usuario.getDescription());
		
		final String record = this.gson.toJson(tuitero);
		Document doc = new Document("User", usuario.getScreenName());
		doc.append("TimeLine", record);
		this.collection.insertOne(doc);
	}
	
	public void getUser(String user)
	{
		//Document doc = this.collection.find(eq("User", user)).first();
		//TwitterUser tuitero = this.gson.fromJson(first.getString("TimeLine"), TwitterUser.class);
		
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
