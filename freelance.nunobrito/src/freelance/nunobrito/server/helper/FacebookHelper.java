package freelance.nunobrito.server.helper;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import freelance.nunobrito.shared.DotClickConstants;

public class FacebookHelper {

	public static Configuration createConfiguration() {
		ConfigurationBuilder confBuilder = new ConfigurationBuilder();

		confBuilder.setDebugEnabled(true);
		confBuilder.setOAuthAppId(DotClickConstants.FACEBOOK_CLIENT_ID);
		confBuilder.setOAuthAppSecret(DotClickConstants.FACEBOOK_APP_SECRET);
		confBuilder.setUseSSL(true);
		confBuilder.setJSONStoreEnabled(true);
		Configuration configuration = confBuilder.build();
		return configuration;
	}

	public static void main(String[] argv) throws FacebookException {
		ConfigurationBuilder confBuilder = new ConfigurationBuilder();
		confBuilder.setDebugEnabled(true);

		confBuilder.setOAuthAppId("970377109645199");
		confBuilder.setOAuthAppSecret("b50cff040155e47565efdb9800630d33");
		confBuilder.setOAuthAccessToken("CAANyjYbrJ48BAPIzUiddkupCLWgYoyjUGAeGhflpE7k7mTDuzpsWeF5ufQmIgXi3vf7RBbZAZATRzfQ7bGXCLlbZBsm86SuVNZCfOJ2CvtyZB4ZAXeHtOLt4Q7FDpnFuritQDThHNYZA8lFe1zPOUAcX9jfpuJRwUBci3aFZA12NiZASxnZCPsHDDOJMpPKyHbL2diQiXk6QSTbr9MVjm1oaw2");

		confBuilder.setOAuthPermissions("offline_access,manage_pages,publish_actions,user_photos,publish_checkins, photo_upload");

		confBuilder.setUseSSL(true);
		confBuilder.setJSONStoreEnabled(true);

		Configuration configuration = confBuilder.build();
		FacebookFactory ff = new FacebookFactory(configuration);
		Facebook facebook = ff.getInstance();
		facebook.postStatusMessage("Hello World from Facebook4J.");
	}

	public static String getFacebookPostes(Facebook facebook)
			throws FacebookException {

		ResponseList<Post> results = facebook.getPosts("Reebok");
		return results.toString();

	}
}
