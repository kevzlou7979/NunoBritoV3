package freelance.nunobrito.server.cron;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Objectify;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import freelance.nunobrito.server.MailSender;
import freelance.nunobrito.server.OfyService;
import freelance.nunobrito.shared.DotClickConstants;
import freelance.nunobrito.shared.User;


public class FacebookCronServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Objectify ofy = OfyService.ofy();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		for(User u : ofy.query(User.class)){
			String permissions = DotClickConstants.FACEBOOK_STATUS_UPDATE + ","
					+ DotClickConstants.FACEBOOK_PUBLISH_ACTIONS + ","
					+ DotClickConstants.FACEBOOK_MANAGE_PAGES;
			Facebook facebook = new FacebookFactory().getInstance();

			facebook.setOAuthAppId(DotClickConstants.FACEBOOK_CLIENT_ID, DotClickConstants.FACEBOOK_APP_SECRET);
			facebook.setOAuthPermissions(permissions);
			facebook.setOAuthAccessToken(new AccessToken(u.getToken(), null));

			PostUpdate fbPost = new PostUpdate(new URL( "http://nuno-brito.appspot.com/"))
					.picture( new URL( "http://p1.pichost.me/i/25/1477351.jpg"))
					.name("Dot Click Post").caption("Dot Click")
					.description(DotClickConstants.LOREM_IPSUM);
			try {
				String postId = facebook.postFeed(fbPost);
				freelance.nunobrito.shared.Post dotClickPost =  new freelance.nunobrito.shared.Post(u.getId(), DotClickConstants.LOREM_IPSUM, DotClickConstants.PHOTO_URL, postId);
				ofy.put(dotClickPost);
				
				getAllUserPosts(facebook, u);
				
			} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Check all the post from facebook if its deleted, IF TRUE we set the post delete to true
	 * @param dotClickPost
	 * @param facebook
	 * @param user 
	 * @throws FacebookException
	 * @throws UnsupportedEncodingException 
	 */
	private void checkPostIfDeleted(freelance.nunobrito.shared.Post dcPost, Facebook facebook, User user) throws FacebookException, UnsupportedEncodingException{
		ResponseList<Post> feed = facebook.getHome();
		boolean isDeleted = false;
		for(Post post : feed){
			if(post.getId().equals(dcPost.getFbPostId())){
				isDeleted = false;
				break;
			}else{
				isDeleted = true;
			}
		}
		dcPost.setDeleted(isDeleted);
		ofy.put(dcPost);
		
		checkFreezeUser(user);
	}
	
	/**
	 * Three String System, IF exceeds three times we must freeze the user
	 * @param user
	 * @throws UnsupportedEncodingException 
	 */
	private void checkFreezeUser(User user) throws UnsupportedEncodingException{
		int deletedPost = 0;
		for(freelance.nunobrito.shared.Post post : ofy.query(freelance.nunobrito.shared.Post.class)){
			if(post.getDeleted()!=null){
				if(post.getDeleted()){
					deletedPost ++;
				}
			}
		}
	
		String to = "nunobrito.azores@gmail.com";
		String subject = "Dotlick Post Notification";
		if(deletedPost == 1){
			new MailSender(to, subject, "You have still 2 strikes left.");
		}else if(deletedPost == 2){
			new MailSender(to, subject, "You have still 1 strike left.");
		}else if(deletedPost >= 3){
			new MailSender(to, subject, "You dont have any strike left, your account has beed temporarily frozen.");
			user.setFreeze(true);
			ofy.put(user);
		}
	}
	
	/**
	 * Get All User Posts from App Post Objects
	 * @param facebook
	 * @throws FacebookException
	 * @throws UnsupportedEncodingException 
	 */
	private void getAllUserPosts(Facebook facebook, User user) throws FacebookException, UnsupportedEncodingException{
		for(freelance.nunobrito.shared.Post post : ofy.query(freelance.nunobrito.shared.Post.class)){
			checkPostIfDeleted(post, facebook, user);
		}
	}
}

