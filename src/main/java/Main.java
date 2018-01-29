import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import org.apache.log4j.BasicConfigurator;

import twitter4j.*;
import twitter4j.Status;


public class Main {

	protected static final String username = "YourOrgUserName";
	protected static final String password = "YourPassAndToken";
	protected static final String SalesOrder = "/event/Sales_Event__e";
	protected static final String TweetPoll = "/event/tweet_Poll__e";

	public static void main(String[] args) throws Exception {

		BasicConfigurator.configure();

		String port = System.getenv("PORT");
		if (port==null)
			port = "8080";

		Server server = new Server(Integer.valueOf(port));
		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);
		handler.addServletWithMapping(WelcomeServlet.class, "/");
		handler.addServletWithMapping(SubscribeServlet.class, "/subscribe");
		handler.addServletWithMapping(FireTweet.class, "/firetweet");
		server.start();
		server.join();
	}

	@SuppressWarnings("serial")
	public static class WelcomeServlet extends HttpServlet {
		@Override
		protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException {
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println("<h1>Welcome: endpoints - /subscribe /firetwitter</h1>");
		}
	}

	@SuppressWarnings("serial")
	public static class FireTweet extends HttpServlet {
		@Override
		protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException {
			try
			{
				response.setContentType("text/html");
				response.setStatus(HttpServletResponse.SC_OK);

				TwitterListenerV3 tInstanceV3 = new TwitterListenerV3();

				for(Status result : tInstanceV3.getMyTimeline()){
					response.getWriter().println("<h1>Result: "+FirePoll.getInstance().pollTweet(result.getText(), result.getUser().getName())+"</h1>");
				}
			} catch(Exception e) 
			{
 				throw new ServletException(e);
			}

		}
	}

	@SuppressWarnings("serial")
	public static class SubscribeServlet extends HttpServlet {
		@Override
		protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException {
			try
			{
				response.setContentType("text/html");
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println("<h1>Listening...</h1>");
				response.getWriter().println("<h1>"+PlatformEventsListener.getInstance().getReceivedEvents()+"</h1>");
				// Refresh servlet every 5 seconds to see if we have received more events
				response.addHeader("Refresh", "5");
			} catch(Exception e) {
 				throw new ServletException(e);
			}
		}
	}

	@SuppressWarnings("serial")
	public static class TwitterListenerServlet extends HttpServlet {
		@Override
		protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException {
			try
			{
				response.setContentType("text/html");
				response.setStatus(HttpServletResponse.SC_OK);
				
				TwitterListenerV3 tInstanceV3 = new TwitterListenerV3();

				for(Status result : tInstanceV3.getMyTimeline()){
					response.getWriter().println("<h1>"+result.getUser().getName()+":</h1>");
					response.getWriter().println("<h1> "+result.getText()+"</h1><br>");
				}
				
				response.addHeader("Refresh", "120");
			}
			catch(Exception e) 
			{
 				throw new ServletException(e);
			}
		}
	}
}
