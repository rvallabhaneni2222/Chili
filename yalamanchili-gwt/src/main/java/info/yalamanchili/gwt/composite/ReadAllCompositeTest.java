package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.beans.TableObj;
import info.yalamanchili.gwt.fields.ListBoxField;
import info.yalamanchili.gwt.utils.Alignment;
import info.yalamanchili.gwt.widgets.ClickableLink;

import java.util.List;
import java.util.MissingResourceException;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public abstract class ReadAllCompositeTest<T> extends ALComposite implements
		ClickHandler, ChangeHandler {

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();

	/** The paging panel. */
	protected HorizontalPanel pagingPanel = new HorizontalPanel();

	/** The table panel. */
	protected VerticalPanel tablePanel = new VerticalPanel();

	/** The table. */
	protected FlexTable table = new FlexTable();

	/** The number of records. */
	protected Long numberOfRecords = new Long(0);

	/** The page size. */
	protected Integer pageSize = 10;// default

	/** The number of pages. */
	protected Integer numberOfPages;

	/** The class canonical name. */
	protected String classCanonicalName;

	/** The constants. */
	protected ConstantsWithLookup constants;

	/** The go to page. */
	protected ListBoxField goToPage = new ListBoxField("Page: ",
			Alignment.HORIZONTAL);

	/** The no of results l. */
	protected Label noOfResultsL = new Label("Total Results:");

	/**
	 * Inits the table.
	 * 
	 * @param classCanonicalName
	 *            the class canonical name
	 * @param constants
	 *            the constants
	 */
	protected void initTable(T t, ConstantsWithLookup constants) {
		this.classCanonicalName = t.getClass().getName();
		this.constants = constants;
		init(panel);
		preFetchTable(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.yalamanchili.gwt.composite.ALComposite#configure()
	 */
	@Override
	protected void configure() {
		table.addStyleName("Table");
		table.getRowFormatter().addStyleName(0, "tableHeader");
		table.setBorderWidth(1);
		pagingPanel.setSpacing(5);
		pagingPanel.addStyleName("PagingBar");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.yalamanchili.gwt.composite.ALComposite#addListeners()
	 */
	@Override
	protected void addListeners() {
		goToPage.addChangeHandler(this);
		table.addClickHandler(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.yalamanchili.gwt.composite.ALComposite#addWidgets()
	 */
	@Override
	protected void addWidgets() {
		pagingPanel.add(goToPage);
		pagingPanel.add(noOfResultsL);
		tablePanel.add(table);
		panel.add(pagingPanel);
		panel.add(tablePanel);
	}

	/**
	 * Inits the load.
	 * 
	 * @param noOfRecords
	 *            the no of records
	 */
	public void initPaging(Long noOfRecords) {
		pageSize = new Integer(10);
		numberOfRecords = noOfRecords;
		noOfResultsL.setText("Total Results:" + noOfRecords.toString());
		createPageLinks();
	}

	/**
	 * Creates the page links.
	 */
	protected void createPageLinks() {
		if (numberOfPages == null || numberOfPages == 0) {
			numberOfPages = (numberOfRecords.intValue() / pageSize) + 1;
			for (int i = 1; i <= numberOfPages; i++) {
				goToPage.addValue(new Long(i), new Integer(i).toString());
			}
		}
	}

	/**
	 * Configure table.
	 */
	public abstract void preFetchTable(int start);

	public void postFetchTable(TableObj table) {
		initPaging(table.getNumberOfRecords());
		createTableHeader();
		fillData(table.getRecords());
	}

	public void postFetchTable(List<T> entities) {
		createTableHeader();
		fillData(entities);
	}

	public abstract void createTableHeader();

	/**
	 * Creates the view icon.
	 * 
	 * @param row
	 *            the row
	 * @param id
	 *            the id
	 */
	protected void createViewIcon(int row, Long id) {
		ClickableLink link = new ClickableLink("view");
		link.setTitle(id.toString());
		table.setWidget(row, 0, link);
	}

	/**
	 * Gets the entity id.
	 * 
	 * @param row
	 *            the row
	 * 
	 * @return the entity id
	 */
	protected Long getEntityId(int row) {
		return new Long(table.getWidget(row, 0).getTitle());
	}

	public abstract void fillData(List<T> entities);

	/**
	 * View clicked.
	 * 
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 */
	public abstract void viewClicked(int row, int col);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public void onClick(ClickEvent event) {
		if (event.getSource() == table) {
			Cell clickedCell = table.getCellForEvent(event);
			if (clickedCell.getRowIndex() != 0)
				viewClicked(clickedCell.getRowIndex(), clickedCell
						.getCellIndex());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ChangeListener#onChange(com.google.gwt.
	 * user.client.ui.Widget)
	 */
	public void onChange(ChangeEvent event) {
		if (event.getSource() == goToPage.getListBox()) {
			preFetchTable((goToPage.getValue().intValue() * pageSize) - 10);
		}
	}

	/**
	 * Gets the class value.
	 * 
	 * @param id
	 *            the id
	 * 
	 * @return the class value
	 */
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

	/**
	 * Gets the key value.
	 * 
	 * @param id
	 *            the id
	 * 
	 * @return the key value
	 */
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
