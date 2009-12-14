package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.fields.BooleanField;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.fields.DateField;
import info.yalamanchili.gwt.fields.EnumField;
import info.yalamanchili.gwt.fields.FloatField;
import info.yalamanchili.gwt.fields.IntegerField;
import info.yalamanchili.gwt.fields.LongField;
import info.yalamanchili.gwt.fields.PasswordField;
import info.yalamanchili.gwt.fields.StringField;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;
import info.yalamanchili.gwt.widgets.ALSuggestBox;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.MissingResourceException;

import net.sf.gilead.pojo.java5.LightEntity;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public abstract class SearchPanelCompositeGeneric<T extends LightEntity>
		extends ReadUpdateCreateCompositeRef<T> implements ClickHandler {
	protected Button searchB = new Button("search");

	public void initSearchPanelCompositeGeneric(T entity,
			final ConstantsWithLookup constants) {
		init(entity, false, constants);
		entityCaptionPanel.addStyleName("y-gwt-SearchEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-SearchEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-SearchBasePanel");
		searchB.addClickHandler(this);
		entityDisplayWidget.add(searchB);
		addListeners();
		addWidgets();
		configure();
	}

	@Override
	protected void addFields(LinkedHashMap<String, DataType> attributes,
			ConstantsWithLookup constants) {
		for (String fieldName : attributes.keySet()) {
			if (!(DataType.DATE_FIELD.equals(attributes.get(fieldName)) || DataType.PASSWORD_FIELD
					.equals(attributes.get(fieldName))))
				try {
					String property = classCanonicalName.replace(".", "_")
							+ "_" + fieldName;
					String value = constants.getString(property);
					// if a different text is specified in the properties file
					addField(fieldName.toUpperCase(), value, attributes
							.get(fieldName));
				} catch (MissingResourceException e) {
					addField(fieldName.toUpperCase(), getCamelCase(fieldName),
							attributes.get(fieldName));
				}
		}
	}

	protected abstract void addListeners();

	protected abstract void addWidgets();

	protected abstract void configure();

	protected abstract void searchButtonClicked(T entity);

	protected void addSuggestBox(String name, List<String> values) {
		ALSuggestBox suggestBox = new ALSuggestBox(getCamelCase(name));
		suggestBox.loadData(values);
		if (fields.containsKey(name.toUpperCase())) {
			int index = entityDisplayWidget.getWidgetIndex((Widget) fields
					.get(name.toUpperCase()));
			entityDisplayWidget.remove((Widget) fields.get(name.toUpperCase()));
			fields.remove(name.toUpperCase());
			fields.put(name.toUpperCase(), suggestBox);
			entityDisplayWidget.insert(suggestBox, index);
		} else {
			Log.error("Errror no field with name present:" + name.toUpperCase());
			Log.debug("Fields Map conttsins:" + fields.keySet().toString());
		}

	}

	protected void populateEntity() {
		GwtServiceAsync.instance().createEntityFromFieldsWithID(
				classCanonicalName, populateEntityFromFields(),
				new ALAsyncCallback<T>() {

					@Override
					public void onResponse(T data) {
						searchButtonClicked(data);

					}

				});
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == searchB) {
			populateEntity();
		}

	}

}
