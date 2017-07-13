package minitwitter;

import java.util.Hashtable;
import javax.swing.DefaultListModel;

public class TwitterUser implements User, Observer, Group, TwitterElement {
	
	private DefaultListModel<TwitterUser> following;
	private DefaultListModel<TwitterUser> followers;
	private DefaultListModel<TweetMessage> newsFeed;
	private DefaultListModel<TweetMessage> archive;
	private TweetMessage tweet;
	private boolean isGroup;
	private Hashtable<String, Group> children;
	private String name;
	private String ID;
	
	public TwitterUser(String name, String ID){
		
		this.name = name;
		this.ID = ID;
		this.isGroup = false;
		following = new DefaultListModel<TwitterUser>();
		followers= new DefaultListModel<TwitterUser>();
		newsFeed = new DefaultListModel<TweetMessage>();
		archive = new DefaultListModel<TweetMessage>();
	}

	@Override
	public void accept(TwitterElementVisitor visitor) {
		
		visitor.visit(this);
		
	}

	@Override
	public void add(Group child) {
		
		children.put(child.getName(), child);
		
	}

	//not implemented
	@Override
	public void remove(TwitterUser user) {
		
		
	}

	@Override
	public Hashtable<String, Group> getChildren() {
		
		return children;
	}

	@Override
	public Group getChild(String name) {
		
		return children.get(name);
	}

	@Override
	public boolean isGroup() {
		
		return isGroup;
	}

	@Override
	public void setGroup(boolean isGroup) {
		
		this.isGroup = isGroup;
		
	}

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public String getID() {
		
		return ID;
	}

	public String toString(){
		
		return getName();
	}
	
	@Override
	public void update(TwitterUser user) {
		
		newsFeed.addElement(user.tweet);
		
	}

	@Override
	public void follow(TwitterUser user) {
		
		if(!following.contains(user)){
			
			following.addElement(user);
			user.getFollowers().addElement(this);
			
			for(int i = 0; i < user.archive.getSize(); i++){
				
				newsFeed.addElement(user.archive.elementAt(i));
			}
			
		}
		
	}
	
	public void post(String message){
		
		tweet = new TweetMessage(message, this);
		newsFeed.addElement(tweet);
		archive.addElement(tweet);
		AdminUI.getInstance().getArchives().add(tweet);
		notifyFollowers();
	}
	
	public void notifyFollowers(){
		
		for(int i = 0; i < followers.getSize(); i++){
			
			followers.elementAt(i).update(this);
		}
	}
	
	public DefaultListModel<TwitterUser> getFollowers(){
		
		return followers;
	}
	
	public DefaultListModel<TwitterUser> getFollowing(){
		
		return following;
	}
	
	
	public DefaultListModel<TweetMessage> getNewsFeed(){
		
		return newsFeed;
	}
	
	



	

}
