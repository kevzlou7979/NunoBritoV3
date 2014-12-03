package freelance.nunobrito.client.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UserFrozeDialog extends Composite {

	private static UserFrozeDialogUiBinder uiBinder = GWT
			.create(UserFrozeDialogUiBinder.class);

	interface UserFrozeDialogUiBinder extends UiBinder<Widget, UserFrozeDialog> {
	}

	public UserFrozeDialog() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
