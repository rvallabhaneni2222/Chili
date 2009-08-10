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
		panel.addStyleName("SearchPanelCompositeGeneric");
		searchB.addClickHandler(this);
		panel.add(searchB);
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
					addField(fieldName.toUpperCase(), fieldName.toUpperCase(),
							attributes.get(fieldName));
				}
		}
	}

	@Override
	protected void addField(String id, String text, DataType type) {
		if (DataType.LONG_FIELD.equals(type)) {
			LongField longField = new LongField(text, readOnly);
			fields.put(id, longField);
			panel.add(longField);
		}
		if (DataType.INTEGER_FIELD.equals(type)) {
			IntegerField integerField = new IntegerField(text, readOnly);
			fields.put(id, integerField);
			panel.add(integerField);
		}
		if (DataType.STRING_FIELD.equals(type)) {
			StringField stringField = new StringField(text, readOnly);
			fields.put(id, stringField);
			panel.add(stringField);
		}
		if (DataType.TEXT_AREA_FIELD.equals(type)) {
			StringField stringField = new StringField(text, readOnly);
			fields.put(id, stringField);
			panel.add(stringField);
		}
		if (DataType.DATE_FIELD.equals(type)) {
			DateField dateField = new DateField(text, readOnly);
			fields.put(id, dateField);
			panel.add(dateField);
		}
		if (DataType.BOOLEAN_FIELD.equals(type)) {
			BooleanField booleanField = new BooleanField(text, readOnly);
			fields.put(id, booleanField);
			panel.add(booleanField);
		}
		if (DataType.ENUM_FIELD.equals(type)) {
			EnumField enumField = new EnumField(text, readOnly);
			fields.put(id, enumField);
			if (!readOnly)
				populateEnumFields(enumField, id);
			panel.add(enumField);
		}
		if (DataType.PASSWORD_FIELD.equals(type)) {
			PasswordField passwordField = new PasswordField(text);
			fields.put(id, passwordField);
			panel.add(passwordField);
		}
		if (DataType.FLOAT_FEILD.equals(type)) {
			FloatField floatField = new FloatField(text, readOnly);
			fields.put(id, floatField);
			panel.add(floatField);
		}

	}

	protected abstract void addListeners();

	protected abstract void addWidgets();

	protected abstract void configure();

	protected abstract void searchButtonClicked(T entity);

	protected void addSuggestBox(String name, List<String> values) {
		ALSuggestBox suggestBox = new ALSuggestBox(name);
		suggestBox.loadData(values);
		int index = panel.getWidgetIndex((Widget) fields.get(name));
		panel.remove((Widget) fields.get(name));
		fields.remove(name);
		fields.put(name, suggestBox);
		panel.insert(suggestBox, index);
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
