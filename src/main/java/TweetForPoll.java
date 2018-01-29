import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetForPoll {

	//I created my event in SF just with this 2 fields, but add as many as you need here
	@JsonProperty("Tweet__c")
	String tweet;
	@JsonProperty("User_Name__c")
	String userName;
	
	public String getTweet() { return tweet; }
	public void setTweet(String tweet) { this.tweet = tweet; }
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
}