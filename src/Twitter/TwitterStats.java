package Twitter;

import java.util.ArrayList;
import java.util.Date;
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
	
	public HashMap<String, Integer> days(ArrayList<String> users)
	{
		HashMap<String, Integer> marc = new HashMap();
		
		marc.put("Domingo", 0);
		marc.put("Lunes", 0);
		marc.put("Martes", 0);
		marc.put("Miercoles", 0);
		marc.put("Jueves", 0);
		marc.put("Viernes", 0);
		marc.put("Sabado", 0);
		
		for (String user : users)
		{
			if (this.validateUser(user))
			{
				this.usuario = twitter.getUser(user);
				List<Status> tweets = twitter.getUserTimeLine(usuario, 1, 200);
				
				if (tweets != null)
				{				
					for (Status tweet: tweets)
					{
						Date dia = tweet.getCreatedAt();
						switch(dia.getDay())
						{
							case 0:
								int cont = marc.get("Domingo");
								cont++;
								marc.put("Domingo", cont);
								break;
							case 1:
								int cont1 = marc.get("Lunes");
								cont1++;
								marc.put("Lunes", cont1);
								break;
							case 2:
								int cont2 = marc.get("Martes");
								cont2++;
								marc.put("Martes", cont2);
								break;
							case 3:
								int cont3 = marc.get("Miercoles");
								cont3++;
								marc.put("Miercoles", cont3);
								break;
							case 4:
								int cont4 = marc.get("Jueves");
								cont4++;
								marc.put("Jueves", cont4);
								break;
							case 5:
								int cont5 = marc.get("Viernes");
								cont5++;
								marc.put("Viernes", cont5);
								break;
							case 6:
								int cont6 = marc.get("Sabado");
								cont6++;
								marc.put("Sabado", cont6);
								break;
						}
					}
				}
			}
		}
		
		return marc;
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
