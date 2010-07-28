package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.beans.TableObj;

import java.util.List;
import java.util.MissingResourceException;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public abstract class SCReadAllComposite<T> extends ALComposite implements
		RecordClickHandler, ChangedHandler {
	protected String classCanonicalName;

	protected FlowPanel panel = new FlowPanel();

	protected final DynamicForm form = new DynamicForm();

	protected HorizontalPanel pagingPanel = new HorizontalPanel();

	protected FlowPanel tablePanel = new FlowPanel();

	protected final ListGrid table = new ListGrid();

	protected Long numberOfRecords = new Long(10);

	protected Integer pageSize = 10;// default

	protected Integer numberOfPages;

	protected ConstantsWithLookup constants;

	protected ComboBoxItem goToPage = new ComboBoxItem();

	protected Label noOfResultsL = new Label("Total Results:");

	protected void initTable(T t, ConstantsWithLookup constants) {
		this.classCanonicalName = t.getClass().getName();
		this.constants = constants;
		init(panel);
		preFetchTable(0);
	}

	protected void initTable(T t, List<T> entities,
			ConstantsWithLookup constants) {
		this.classCanonicalName = t.getClass().getName();
		this.constants = constants;
		init(panel);
		postFetchTable(entities);
	}

	@Override
	protected void configure() {
		goToPage.setTitle("Page Number:");
		table.addStyleName("y-gwt-Table");
		pagingPanel.setSpacing(5);
		pagingPanel.addStyleName("y-gwt-PagingBar");
		panel.addStyleName("y-gwt-ReadAllPanel");
		tablePanel.addStyleName("y-gwt-TablePanel");
	}

	@Override
	protected void addListeners() {
		goToPage.addChangedHandler(this);
		table.addRecordClickHandler(this);
	}

	@Override
	protected void addWidgets() {
		form.setFields(goToPage);
		pagingPanel.add(noOfResultsL);
		configureTable();
		configureFields();
		tablePanel.add(table);
		// panel.add(pagingPanel);
		panel.add(tablePanel);
		panel.add(form);
	}

	public void initPaging(Long noOfRecords) {
		this.numberOfRecords = noOfRecords;
		noOfResultsL.setText("Total Results:" + noOfRecords.toString());
		createPageLinks();
	}

	protected void createPageLinks() {
		if (numberOfPages == null || numberOfPages == 0) {
			numberOfPages = (numberOfRecords.intValue() / pageSize) + 1;
			for (int i = 1; i <= numberOfPages; i++) {
				goToPage.setAttribute(new Long(i).toString(), i);
			}
		}
	}

	public abstract void preFetchTable(int start);

	public void postFetchTable(TableObj table) {
		initPaging(table.getNumberOfRecords());
		fillData(table.getRecords());
	}

	public void postFetchTable(List<T> entities) {
		fillData(entities);
	}

	public abstract void configureTable();

	public abstract void configureFields();

	public abstract void fillData(List<T> entities);

	public abstract void viewClicked(int row, int col);

	// public void onChange(ChangeEvent event) {
	// if (event.getSource() == goToPage.getListBox()) {
	// preFetchTable((goToPage.getValue().intValue() * pageSize) - 10);
	// }
	// }

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

	@Override
	public void onRecordClick(RecordClickEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChanged(ChangedEvent event) {
		// TODO Auto-generated method stub

	}

}
