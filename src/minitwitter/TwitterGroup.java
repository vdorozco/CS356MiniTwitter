package minitwitter;

import java.util.Hashtable;

public class TwitterGroup implements Group, TwitterElement{
	
	private boolean isGroup;
	private Hashtable<String, Group> children;
	private String name;
	private String ID;

	public TwitterGroup(String name, String ID){
		
		this.name = name;
		this.ID = ID;
		this.isGroup = true;
		this.children = new Hashtable<String, Group>();
		
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
		
		return name;
	}


}
