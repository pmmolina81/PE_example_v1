import java.util.List;
import java.util.stream.Collectors;

import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterListenerV3 {

	public static Twitter getTwitterV3instance() {
		
		ConfigurationBuilder cf = new ConfigurationBuilder();

		cf.setDebugEnabled(true)
			.setOAuthConsumerKey("yourSetOAuthConsumerKey")
            .setOAuthConsumerSecret("yourSetOAuthConsumerSecret")
            .setOAuthAccessToken("yourSetOAuthAccessToken")
            .setOAuthAccessTokenSecret("yoursetOAuthAccessTokenSecret");

		TwitterFactory tf = new TwitterFactory(cf.build());
		Twitter tweets = tf.getInstance();

		return tweets;		
	}
	
	public static List<Status> getMyTimeline() throws TwitterException {
		Twitter twitter = getTwitterV3instance();
		List<Status> statuses = twitter.getUserTimeline();
		return statuses;
	}
	
}