package info.yalamanchili.gwt.composite;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc
/**
 * The Class ALComposite.
 */
public abstract class ALComposite extends Composite {

	/**
	 * Adds the listeners.
	 */
	protected abstract void addListeners();

	/**
	 * Configure.
	 */
	protected abstract void configure();

	/**
	 * Adds the widgets.
	 */
	protected abstract void addWidgets();

	/**
	 * Inits the.
	 * 
	 * @param widget the widget
	 */
	protected void init(Widget widget) {
		initWidget(widget);
		addListeners();
		configure();
		addWidgets();
	}
}
