package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

// TODO: Auto-generated Javadoc
/**
 * The Class EnumField.
 */
public class EnumField extends BaseField {
	Logger logger = Logger.getLogger(EnumField.class.getName());
	protected final ListBox listBox = new ListBox();

	@UiConstructor
	public EnumField(String labelName, String attributeName, String className,
			Boolean readOnly, Boolean isRequired) {
		super(labelName, attributeName, className, readOnly, isRequired);
		configureAddMainWidget();
		GwtServiceAsync.instance().getEnumValues(className, attributeName,
				new AsyncCallback<Enum<?>[]>() {

					public void onFailure(Throwable caught) {
						logger.log(Level.SEVERE, caught.getMessage());
					}

					public void onSuccess(Enum<?>[] result) {
						for (Enum e : result) {
							listBox.addItem(e.toString(), e.toString());
						}
					}

				});
	}

	public EnumField(String labelName, String attributeName, String className,
			Boolean readOnly, Boolean isRequired, String[] values) {
		super(labelName, attributeName, className, readOnly, isRequired);
		configureAddMainWidget();
		for (String value : values) {
			listBox.addItem(value.toString(), value.toString());
		}
	}

	public void setValue(String var) {
		listBox.clear();
		listBox.addItem(var, var);
	}

	public String getValue() {
		return listBox.getItemText(listBox.getSelectedIndex());
	}

	@Override
	protected void configureAddMainWidget() {
		fieldPanel.insert(listBox, 0);
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}
}
