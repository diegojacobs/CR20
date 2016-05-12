package Twitter;

import java.util.Date;

import twitter4j.HashtagEntity;
import twitter4j.Place;
import twitter4j.UserMentionEntity;

/**
@author Diego Jacobs
Date: May 11, 2016
 */
public class Tweet {
	private Date fecha;
	private String tweet;
	private UserMentionEntity[] mentions;
	private int cantRetweets;
	private int cantLikes;
	private Place lugar;
	private HashtagEntity[] Hashtags;
	
	public Tweet(Date fecha, String tweet, UserMentionEntity[] mentions,
			int cantRetweets, int cantLikes, Place lugar, HashtagEntity[] hashtags) {
		super();
		this.fecha = fecha;
		this.tweet = tweet;
		this.mentions = mentions;
		this.cantRetweets = cantRetweets;
		this.cantLikes = cantLikes;
		this.lugar = lugar;
		this.Hashtags = hashtags;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public UserMentionEntity[] getMentions() {
		return mentions;
	}

	public void setMentions(UserMentionEntity[] mentions) {
		this.mentions = mentions;
	}

	public int getCantRetweets() {
		return cantRetweets;
	}

	public void setCantRetweets(int cantRetweets) {
		this.cantRetweets = cantRetweets;
	}

	public int getCantLikes() {
		return cantLikes;
	}

	public void setCantLikes(int cantLikes) {
		this.cantLikes = cantLikes;
	}

	public Place getLugar() {
		return lugar;
	}

	public void setLugar(Place lugar) {
		this.lugar = lugar;
	}

	public HashtagEntity[] getHashtags() {
		return Hashtags;
	}

	public void setHashtags(HashtagEntity[] hashtags) {
		Hashtags = hashtags;
	}
}
