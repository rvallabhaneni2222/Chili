package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;
import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public abstract class CreateCompositeRef<T extends LightEntity> extends
		ReadUpdateCreateCompositeRef<T> implements ClickListener {

	public Button create = new Button("CREATE");

	public void initCreateComposite(T t, ConstantsWithLookup constants,
			Messages messages) {
		init(t, false, constants);
	}

	protected void postInit() {
		panel.setSpacing(5);
		panel.add(create);
		removeField("ID");
		create.addClickListener(this);
	}

	protected void preCreateButtonClicked() {
		GwtServiceAsync.instance().createEntityFromFields(classCanonicalName,
				populateEntityFromFields(), new ALAsyncCallback<T>() {

					@Override
					public void onResponse(T data) {
						entity = data;
						createButtonClicked();

					}

				});
	}

	protected abstract void createButtonClicked();

	public void onClick(Widget widget) {
		if (widget == create) {
			preValidate();
		}
	}

	protected void postValidate() {
		if (postValidateImpl())
			preCreateButtonClicked();
	}

	protected T createEntityFromFields() {
		return null;
	}
}
