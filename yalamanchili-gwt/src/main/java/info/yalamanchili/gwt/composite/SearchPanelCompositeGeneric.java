package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.widgets.ALSuggestBox;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.gilead.pojo.gwt.LightEntity;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public abstract class SearchPanelCompositeGeneric<T extends LightEntity>
		extends ReadUpdateCreateComposite<T> implements ClickHandler {
	Logger logger = Logger.getLogger(SearchPanelCompositeGeneric.class
			.getName());
	protected Button searchB = new Button("search");

	public void initSearchPanelCompositeGeneric(String className,
			final ConstantsWithLookup constants) {
		init(className, false, constants);
		entityCaptionPanel.addStyleName("y-gwt-SearchEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-SearchEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-SearchBasePanel");
		searchB.addClickHandler(this);
		entityDisplayWidget.add(searchB);
	}

	protected abstract void addListeners();

	protected abstract void addWidgets();

	protected abstract void configure();

	protected abstract void searchButtonClicked();

	protected void addSuggestBox(String name, List<String> values) {
		ALSuggestBox suggestBox = new ALSuggestBox(name);
		suggestBox.loadData(values);
		if (fields.containsKey(name)) {
			int index = entityDisplayWidget.getWidgetIndex((Widget) fields
					.get(name));
			entityDisplayWidget.remove((Widget) fields.get(name));
			fields.remove(name);
			fields.put(name, suggestBox);
			entityDisplayWidget.insert(suggestBox, index);
		} else {
			logger.log(Level.WARNING, "Errror no field with name present:"
					+ name.toUpperCase());
			logger.log(Level.INFO, "Fields Map contains:"
					+ fields.keySet().toString());
		}

	}

	protected void search() {
		this.entity = populateEntity();
		searchButtonClicked();
	}

	protected abstract T populateEntity();

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == searchB) {
			search();
		}

	}
}
