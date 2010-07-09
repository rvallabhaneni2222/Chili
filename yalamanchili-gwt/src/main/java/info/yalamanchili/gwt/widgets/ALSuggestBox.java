package info.yalamanchili.gwt.widgets;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;

// TODO: Auto-generated Javadoc
/**
 * The Class ALSuggestBox.
 */
public class ALSuggestBox extends Composite {

	/** The panel. */
	FlowPanel panel = new FlowPanel();

	/** The label. */
	Label label = new Label();

	/** The data. */
	MultiWordSuggestOracle data = new MultiWordSuggestOracle();

	/** The box. */
	SuggestBox box = new SuggestBox(data);

	/**
	 * Instantiates a new aL suggest box.
	 * 
	 * @param name
	 *            the name
	 */
	public ALSuggestBox(String name) {
		initWidget(panel);
		label.setText(name);
		panel.add(label);
		panel.add(box);
	}

	/**
	 * Load data.
	 * 
	 * @param inputs
	 *            the inputs
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
