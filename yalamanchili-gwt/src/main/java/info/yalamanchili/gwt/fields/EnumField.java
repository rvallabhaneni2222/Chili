package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

// TODO: Auto-generated Javadoc
/**
 * The Class EnumField.
 */
public class EnumField extends BaseField {
	Logger logger = Logger.getLogger(EnumField.class.getName());
	protected final ListBox listBox = new ListBox();

	public EnumField(String text, Boolean readOnly, String attributeName,
			String className) {
		super(text);
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
}
