package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;
import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public abstract class UpdateCompositeRef<T extends LightEntity> extends
		ReadUpdateCreateCompositeRef<T> implements ClickListener {

	protected Button update = new Button("UPDATE");

	public void initUpdateComposite(T t, ConstantsWithLookup constants) {
		init(t, false, constants);

	}

	public abstract void populateFields();

	protected void preUpdateButtonClicked() {
		GwtServiceAsync.instance().updateEntityFromFields(entity,
				populateEntityFromFields(), new ALAsyncCallback<T>() {

					@Override
					public void onResponse(T data) {
						entity = data;
						updateButtonClicked();

					}

				});
	}

	public abstract void updateButtonClicked();

	public void onClick(Widget widget) {
		if (widget == update) {
			preValidate();
		}
	}

	@Override
	protected void postInit() {
		update.addClickListener(this);
		panel.add(update);
		removeField("ID");
		populateFields();
	}

	protected void postValidate() {
		if (postValidateImpl())
			preUpdateButtonClicked();
	}

}
