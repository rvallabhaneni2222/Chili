package info.yalamanchili.gwt.utils;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class GenericTable extends Composite implements TableListener {

	protected VerticalPanel panel = new VerticalPanel();
	protected PagingBar pagingBar = new PagingBar();
	protected FlexTable table = new FlexTable();

	public GenericTable() {
		initWidget(panel);
	}

	protected void init() {
		addListeners();
		configure();
		addWidgets();
	}

	protected void initTable() {
		table.addTableListener(this);
		panel.add(table);
		panel.add(pagingBar);
	}

	protected abstract void addListeners();

	protected abstract void addWidgets();

	protected abstract void configure();

	public void onCellClicked(SourcesTableEvents arg0, int row, int col) {
		viewClicked(row, col);
	}

	public abstract void viewClicked(int row, int col);

	public void onClick(Widget arg0) {
		// TODO Auto-generated method stub

	}

}
