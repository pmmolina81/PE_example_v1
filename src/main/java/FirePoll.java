import com.force.api.ApiVersion;
import com.force.api.ApiConfig;
import com.force.api.ForceApi;

public class FirePoll {

	private static FirePoll instance;
	private ForceApi api;

	private FirePoll()
	{
		ApiConfig config = new ApiConfig().setApiVersion(ApiVersion.V39);
		config.setUsername(Main.username);
		config.setPassword(Main.password);

		this.api = new ForceApi(config);
	}

	// Singleton for login just once
	public static FirePoll getInstance() {
		if (instance == null) {
			instance = new FirePoll();
		}
		return instance;
	}

	public String pollTweet(String tweet, String userName) {
		TweetForPoll event = new TweetForPoll();
		event.setTweet(tweet);
		event.setUserName(userName);

		String id = this.api.createSObject("tweet_Poll__e", event);

		return String.format("Tweet sent: %s", id);
	}
}