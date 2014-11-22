package freelance.nunobrito.client.item;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import freelance.nunobrito.client.page.MainPage;
import freelance.nunobrito.client.services.UserService;
import freelance.nunobrito.shared.Post;
import freelance.nunobrito.shared.TimeChecker;

public class PostItem extends Composite {

	private static PostItemUiBinder uiBinder = GWT
			.create(PostItemUiBinder.class);

	interface PostItemUiBinder extends UiBinder<Widget, PostItem> {
	}

	@UiField Label lblMessage, lblTime;
	
	private Post post;
	private MainPage mainPage;
	
	public PostItem(Post post, MainPage mainPage) {
		initWidget(uiBinder.createAndBindUi(this));
		this.post = post;
		this.mainPage = mainPage;
		lblMessage.setText(post.getMessage());
		lblTime.setText("Published " + new TimeChecker(post.getPostDate()).getTimePassed());
	} 

	@UiHandler("btnPostToFB")
	void postToFB(ClickEvent e){
		UserService.Connect.getService().postToFacebook(post, mainPage.getUser().getToken(), new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				Window.alert("Successfully Posted to your Profile Page");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
}
