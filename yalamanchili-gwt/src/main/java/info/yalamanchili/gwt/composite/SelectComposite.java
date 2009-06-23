package info.yalamanchili.gwt.composite;


import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class SelectComposite extends ALComposite {
	protected VerticalPanel panel = new VerticalPanel();
	protected Label label = new Label();
	protected ListBox listBox = new ListBox();

	public SelectComposite(String text) {
		label.setText(text);
		panel.add(label);
		panel.add(listBox);
		panel.setSpacing(5);
		initListBox();
	}

	protected abstract void initListBox();

}
