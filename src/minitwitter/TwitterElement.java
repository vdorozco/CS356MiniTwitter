package minitwitter;

public interface TwitterElement {

	public void accept(TwitterElementVisitor visitor);
}
