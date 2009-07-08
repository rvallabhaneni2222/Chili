package info.yalamanchili.gwt.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericTable.
 */
public abstract class GenericTable extends Composite implements TableListener {

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();
	
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
		table.addTableListener(this);
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

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.TableListener#onCellClicked(com.google.gwt.user.client.ui.SourcesTableEvents, int, int)
	 */
	public void onCellClicked(SourcesTableEvents arg0, int row, int col) {
		viewClicked(row, col);
	}

	/**
	 * View clicked.
	 * 
	 * @param row the row
	 * @param col the col
	 */
	public abstract void viewClicked(int row, int col);

	/**
	 * On click.
	 * 
	 * @param arg0 the arg0
	 */
	public void onClick(Widget arg0) {
		// TODO Auto-generated method stub

	}

}
