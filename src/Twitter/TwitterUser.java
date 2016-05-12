package Twitter;

import java.util.ArrayList;
import java.util.Date;

/**
@author Diego Jacobs
Date: May 11, 2016
 */
public class TwitterUser {
	private long ID;
	private String User;
	private String Location;
	private ArrayList<Tweet> TimeLine;
	private Date created;
	private String Description;
	
	public TwitterUser(long ID, String user, String location,
			ArrayList<Tweet> timeLine, Date created, String description) {
		super();
		this.ID = ID;
		User = user;
		Location = location;
		TimeLine = timeLine;
		this.created = created;
		Description = description;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public ArrayList<Tweet> getTimeLine() {
		return TimeLine;
	}

	public void setTimeLine(ArrayList<Tweet> timeLine) {
		TimeLine = timeLine;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
}
