package freelance.nunobrito.client.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class DotClickDialog extends DialogBox {

	private static DotClickDialogUiBinder uiBinder = GWT
			.create(DotClickDialogUiBinder.class);

	interface DotClickDialogUiBinder extends UiBinder<Widget, DotClickDialog> {
	}

	@UiField
	HTMLPanel dialogPanel;
	private Widget composite;

	public DotClickDialog(Widget composite, double top, double left, double width) {
		
		setWidget(uiBinder.createAndBindUi(this));
		this.getElement().getStyle().setWidth(width, Unit.PCT);
		this.setAutoHideEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.getElement().getStyle().setBackgroundColor("#fff");
		dialogPanel.add(composite);
		this.setModal(true);
		this.setGlassEnabled(true);
		this.getElement().getStyle().setTop(top, Unit.PCT);
		this.getElement().getStyle().setLeft(left, Unit.PCT);
		
	}
	
	
	
	@UiHandler("btnClose")
	void onCloseDialog(ClickEvent e) {
		hide();
	}



	public Widget getComposite() {
		return composite;
	}



	public void setComposite(Widget composite) {
		this.composite = composite;
	}
}
