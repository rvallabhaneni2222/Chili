package info.yalamanchili.gwt.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericTable.
 */
public abstract class GenericTable extends Composite implements ClickHandler {

	/** The panel. */
	protected FlowPanel panel = new FlowPanel();

	/** The paging bar. */
	protected PagingBar pagingBar = new PagingBar();

	/** The table. */
	protected FlexTable table = new FlexTable();

	/**
	 * Instantiates a new generic table.
	 */
	public GenericTable() {
		initWidget(panel);
	}

	/**
	 * Inits the.
	 */
	protected void init() {
		addListeners();
		configure();
		addWidgets();
	}

	/**
	 * Inits the table.
	 */
	protected void initTable() {
		table.addClickHandler(this);
		panel.add(table);
		panel.add(pagingBar);
	}

	/**
	 * Adds the listeners.
	 */
	protected abstract void addListeners();

	/**
	 * Adds the widgets.
	 */
	protected abstract void addWidgets();

	/**
	 * Configure.
	 */
	protected abstract void configure();

	public void onClick(ClickEvent event) {
		if (event.getSource().equals(table))
			viewClicked(table.getCellForEvent(event).getRowIndex(), table
					.getCellForEvent(event).getCellIndex());
	}

	/**
	 * View clicked.
	 * 
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 */
	public abstract void viewClicked(int row, int col);

	/**
	 * On click.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public void onClick(Widget arg0) {
		// TODO Auto-generated method stub

	}

}
