package info.yalamanchili.gwt.composite;


import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class SideBarComposite extends ALComposite implements
		ClickListener {
	protected VerticalPanel panel = new VerticalPanel();

	public SideBarComposite() {
	}

	public abstract void onClick(Widget widget);
}
