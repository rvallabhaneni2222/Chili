package info.yalamanchili.gwt.composite;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class TreePanelComposite<T> extends Composite implements
		ClickListener {
	protected T entity;
	protected VerticalPanel panel = new VerticalPanel();
	protected Tree tree = new Tree();

	public T getEntity() {
		return entity;
	}

	public void initTreePanelComposite(Long id) {
		initWidget(panel);
		addListeners();
		configure();
		addWidgets();
		loadEntity(id);
		panel.add(tree);
	}

	public void initTreePanelComposite() {
		initWidget(panel);
		addListeners();
		configure();
		addWidgets();
		panel.add(tree);
	}

	protected abstract void addListeners();

	protected abstract void configure();

	protected abstract void addWidgets();

	public abstract void onClick(Widget widget);

	public abstract void loadEntity(Long id);
}
