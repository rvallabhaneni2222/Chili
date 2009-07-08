package info.yalamanchili.gwt.composite;


import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class SelectComposite.
 */
public abstract class SelectComposite extends ALComposite {
	
	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();
	
	/** The label. */
	protected Label label = new Label();
	
	/** The list box. */
	protected ListBox listBox = new ListBox();

	/**
	 * Instantiates a new select composite.
	 * 
	 * @param text the text
	 */
	public SelectComposite(String text) {
		label.setText(text);
		panel.add(label);
		panel.add(listBox);
		panel.setSpacing(5);
		initListBox();
	}

	/**
	 * Inits the list box.
	 */
	protected abstract void initListBox();

}
