package info.yalamanchili.gwt.composite;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public abstract class UpdateComposite<T> extends ReadUpdateCreateComposite
		implements ClickListener {
	protected T entity;

	public T getEntity() {
		return entity;
	}

	protected Button update = new Button("UPDATE");

	public void initUpdateComposite() {
		init();
		panel.add(update);
		update.addClickListener(this);
		populateFields();
	}

	public abstract void populateFields();

	public abstract void updateButtonClicked();

	public void onClick(Widget widget) {
		if (widget == update) {
			updateButtonClicked();
		}
	}

}
