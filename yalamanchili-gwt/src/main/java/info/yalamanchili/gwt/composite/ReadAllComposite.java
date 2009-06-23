package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.fields.ListBoxField;
import info.yalamanchili.gwt.utils.Alignment;
import info.yalamanchili.gwt.utils.ClickableLink;

import java.util.List;
import java.util.MissingResourceException;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class ReadAllComposite<T> extends ALComposite implements
		TableListener, ClickListener, ChangeListener {
	protected VerticalPanel panel = new VerticalPanel();
	protected HorizontalPanel pagingPanel = new HorizontalPanel();
	protected VerticalPanel tablePanel = new VerticalPanel();
	protected FlexTable table = new FlexTable();
	protected Long numberOfRecords;
	protected Integer pageSize = 10;// default
	protected Integer numberOfPages;
	protected String classCanonicalName;
	protected ConstantsWithLookup constants;
	protected ListBoxField goToPage = new ListBoxField("Page: ",
			Alignment.HORIZONTAL);
	protected Label noOfResultsL = new Label("Total Results:");

	protected void initTable(String classCanonicalName,
			ConstantsWithLookup constants) {
		this.classCanonicalName = classCanonicalName;
		this.constants = constants;
		init(panel);
		configureTable();
	}

	@Override
	protected void configure() {
		table.addStyleName("Table");
		table.getRowFormatter().addStyleName(0, "tableHeader");
		table.setBorderWidth(2);
		pagingPanel.setSpacing(5);
		pagingPanel.addStyleName("PagingBar");
	}

	@Override
	protected void addListeners() {
		goToPage.addChangeListener(this);
		table.addTableListener(this);
	}

	@Override
	protected void addWidgets() {
		pagingPanel.add(goToPage);
		pagingPanel.add(noOfResultsL);

		tablePanel.add(table);
		panel.add(pagingPanel);
		panel.add(tablePanel);
	}

	public void initLoad(Long noOfRecords) {
		pageSize = new Integer(10);
		numberOfRecords = noOfRecords;
		noOfResultsL.setText("Total Results:" + noOfRecords.toString());
		createPageLinks();
		loadPage(0);
	}

	protected void createPageLinks() {
		numberOfPages = (numberOfRecords.intValue() / pageSize) + 1;
		for (int i = 1; i <= numberOfPages; i++) {
			goToPage.addValue(new Long(i), new Integer(i).toString());
		}
	}

	public abstract void configureTable();

	public abstract void loadPage(int startIndex);

	public void populateTable(List<T> entities) {
		createTableHeader();
		fillTable(entities);
	}

	public abstract void createTableHeader();

	protected void createViewIcon(int row, Long id) {
		ClickableLink link = new ClickableLink("view");
		link.setTitle(id.toString());
		table.setWidget(row, 0, link);
	}

	protected Long getEntityId(int row) {
		return new Long(table.getWidget(row, 0).getTitle());
	}

	public void fillTable(List<T> entities) {
		/*
		 * for (T t : entities) {
		 * 
		 * }
		 */
		fillData(entities);
	}

	public abstract void fillData(List<T> entities);

	public void onCellClicked(SourcesTableEvents arg0, int row, int col) {
		if (row != 0)
			viewClicked(row, col);
	}

	public abstract void viewClicked(int row, int col);

	public void onClick(Widget widget) {

	}

	public void onChange(Widget widget) {
		if (widget == goToPage.getListBox()) {
			loadPage((goToPage.getValue().intValue() * pageSize) - 10);
		}
	}

	protected String getClassValue(String id) {
		String property = classCanonicalName.replace(".", "_") + "_" + id;
		String value = "";
		try {
			value = constants.getString(property);
		} catch (MissingResourceException e) {
			value = id;
		}
		return value;
	}

	protected String getKeyValue(String id) {
		String value = "";
		try {
			value = constants.getString(id);
		} catch (MissingResourceException e) {
			value = id;
		}
		return value;
	}
}
