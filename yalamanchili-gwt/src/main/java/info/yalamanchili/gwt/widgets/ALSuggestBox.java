package info.yalamanchili.gwt.widgets;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ALSuggestBox.
 */
public class ALSuggestBox extends Composite {
	
	/** The panel. */
	VerticalPanel panel = new VerticalPanel();
	
	/** The label. */
	Label label = new Label();
	
	/** The data. */
	MultiWordSuggestOracle data = new MultiWordSuggestOracle();
	
	/** The box. */
	SuggestBox box = new SuggestBox(data);

	/**
	 * Instantiates a new aL suggest box.
	 * 
	 * @param name the name
	 */
	public ALSuggestBox(String name) {
		initWidget(panel);
		label.setText(name);
		panel.add(label);
		panel.add(box);
		panel.setSpacing(5);
	}

	/**
	 * Load data.
	 * 
	 * @param inputs the inputs
	 */
	public void loadData(List<String> inputs) {
		data.addAll(inputs);
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return box.getText();
	}
}
