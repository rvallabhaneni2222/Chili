package info.yalamanchili.gwt.composite;

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

// TODO: Auto-generated Javadoc
/**
 * The Class ReadAllComposite.
 */
public abstract class ReadAllComposite<T> extends ALComposite implements
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
	protected Long numberOfRecords;

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
	protected void initTable(String classCanonicalName,
			ConstantsWithLookup constants) {
		this.classCanonicalName = classCanonicalName;
		this.constants = constants;
		init(panel);
		configureTable();
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
	public void initLoad(Long noOfRecords) {
		pageSize = new Integer(10);
		numberOfRecords = noOfRecords;
		noOfResultsL.setText("Total Results:" + noOfRecords.toString());
		createPageLinks();
		loadPage(0);
	}

	/**
	 * Creates the page links.
	 */
	protected void createPageLinks() {
		numberOfPages = (numberOfRecords.intValue() / pageSize) + 1;
		for (int i = 1; i <= numberOfPages; i++) {
			goToPage.addValue(new Long(i), new Integer(i).toString());
		}
	}

	/**
	 * Configure table.
	 */
	public abstract void configureTable();

	/**
	 * Load page.
	 * 
	 * @param startIndex
	 *            the start index
	 */
	public abstract void loadPage(int startIndex);

	/**
	 * Populate table.
	 * 
	 * @param entities
	 *            the entities
	 */
	public void populateTable(List<T> entities) {
		createTableHeader();
		fillTable(entities);
	}

	/**
	 * Creates the table header.
	 */
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

	/**
	 * Fill table.
	 * 
	 * @param entities
	 *            the entities
	 */
	public void fillTable(List<T> entities) {
		/*
		 * for (T t : entities) {
		 * 
		 * }
		 */
		fillData(entities);
	}

	/**
	 * Fill data.
	 * 
	 * @param entities
	 *            the entities
	 */
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
			loadPage((goToPage.getValue().intValue() * pageSize) - 10);
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
