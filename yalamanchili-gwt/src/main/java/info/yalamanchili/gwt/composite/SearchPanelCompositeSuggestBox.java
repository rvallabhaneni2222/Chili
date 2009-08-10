package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.widgets.ALSuggestBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchPanelComposite.
 */
public abstract class SearchPanelCompositeSuggestBox<T> extends Composite
		implements ClickHandler {

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();

	/** The search b. */
	Button searchB = new Button("Search");

	/** The fields. */
	Map<String, ALSuggestBox> fields = new HashMap<String, ALSuggestBox>();

	/**
	 * Adds the suggest box.
	 * 
	 * @param attributeName
	 *            the attribute name
	 */
	public void addSuggestBox(String attributeName) {
		ALSuggestBox keyWordSuggestBox = new ALSuggestBox(attributeName);
		fields.put(attributeName, keyWordSuggestBox);
		panel.add(keyWordSuggestBox);
	}

	/**
	 * Inits the search composite.
	 */
	public void initSearchComposite() {
		initWidget(panel);
		searchB.addClickHandler(this);
		addListeners();
		addWidgets();
		configure();
		populateSuggestBoxs();
		panel.add(searchB);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public void onClick(ClickEvent event) {
		if (event.getSource() == searchB) {
			searchButtonClicked();
		}
	}

	/**
	 * Populate suggest boxs.
	 */
	public abstract void populateSuggestBoxs();

	/**
	 * Search button clicked.
	 */
	public abstract void searchButtonClicked();

	/**
	 * Adds the listeners.
	 */
	protected abstract void addListeners();

	/**
	 * Configure.
	 */
	protected abstract void configure();

	/**
	 * Adds the widgets.
	 */
	protected abstract void addWidgets();

	/**
	 * Gets the key word text.
	 * 
	 * @param fieldName
	 *            the field name
	 * 
	 * @return the key word text
	 */
	public String getKeyWordText(String fieldName) {
		return fields.get(fieldName).getText();
	}

	/**
	 * Load data.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param data
	 *            the data
	 */
	public void loadData(String fieldName, List<String> data) {
		fields.get(fieldName).loadData(data);
	}
}
