package freelance.nunobrito.client.services;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import freelance.nunobrito.shared.Post;
import freelance.nunobrito.shared.User;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	public static class Connect {

		private static UserServiceAsync service;

		public static UserServiceAsync getService() {
			if (service == null) {
				service = (UserServiceAsync) GWT.create(UserService.class);
			}

			return service;
		}
	}
	
	public User registerUser(String authUrl) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public User savePost(Post post) throws Exception;
	
	public List<Post> getAllUserPost(Long userId) throws Exception;
	
	public void postToFacebook(Post post ,String accessToken) throws Exception;
} 
