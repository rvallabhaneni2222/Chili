package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.widgets.ALSuggestBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class SearchPanelComposite<T> extends Composite implements
		ClickListener {
	protected VerticalPanel panel = new VerticalPanel();

	Button searchB = new Button("Search");

	Map<String, ALSuggestBox> fields = new HashMap<String, ALSuggestBox>();

	public void addSuggestBox(String attributeName) {
		ALSuggestBox keyWordSuggestBox = new ALSuggestBox(attributeName);
		fields.put(attributeName, keyWordSuggestBox);
		panel.add(keyWordSuggestBox);
	}

	public void initSearchComposite() {
		initWidget(panel);
		searchB.addClickListener(this);
		panel.add(searchB);
		addListeners();
		addWidgets();
		configure();
		populateSuggestBoxs();
	}

	public void onClick(Widget arg0) {
		searchButtonClicked();

	}

	public abstract void populateSuggestBoxs();

	public abstract void searchButtonClicked();

	protected abstract void addListeners();

	protected abstract void configure();

	protected abstract void addWidgets();

	public String getKeyWordText(String fieldName) {
		return fields.get(fieldName).getText();
	}

	public void loadData(String fieldName, List<String> data) {
		fields.get(fieldName).loadData(data);
	}
}
