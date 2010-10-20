package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.widgets.ALSuggestBox;

import java.util.List;

import net.sf.gilead.pojo.gwt.LightEntity;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public abstract class SearchPanelCompositeGeneric<T extends LightEntity> extends
		ReadUpdateCreateComposite<T> implements ClickHandler {
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
			Log
					.error("Errror no field with name present:"
							+ name.toUpperCase());
			Log.debug("Fields Map contains:" + fields.keySet().toString());
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
