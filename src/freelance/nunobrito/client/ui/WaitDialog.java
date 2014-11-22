package freelance.nunobrito.client.ui;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import freelance.nunobrito.client.resouces.DotClickResources;

public class WaitDialog {

	private static HTMLPanel panel;
	private static HTMLPanel loader;
	private static Label label;
	
	public static void show(String message, boolean isShow){
		panel = new HTMLPanel("");
		loader = new HTMLPanel("");
		label = new Label(message);
		loader.addStyleName(DotClickResources.INSTANCE.dotclickcss().spinner());
		panel.add(loader);
		panel.add(label);
		if(isShow){
			RootPanel.get().add(panel);
		}else{
			panel.removeFromParent();
		}
	}
	
	public static void hide(){
		panel.removeFromParent();
	}
	
}
