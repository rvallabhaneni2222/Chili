package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.beans.TableObj;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.FlowPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
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

	protected final ListGrid table = new ListGrid();
	/* total records for the table */
	protected Long numberOfRecords = new Long(10);
	/*
	 * make sure to update the server side component attribute to the same value
	 * for paging to work (getEntitites.setMaxResults(10);)
	 */
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
		goToPage.setTitle("PageNumber:");
		goToPage.setWidth(40);
		table.setWidth100();
		table.setHeight100();
		table.setShowAllRecords(true);
		table.setAlternateRecordStyles(true);
		table.setCanResizeFields(true);
	}

	@Override
	protected void addListeners() {
		goToPage.addChangedHandler(this);
		table.addRecordClickHandler(this);
	}

	@Override
	protected void addWidgets() {
		form.setFields(goToPage);
		configureTable();
		configureFields();
		panel.add(form);
		panel.add(table);
		panel.add(noOfResultsL);
	}

	public void initPaging(Long noOfRecords) {
		this.numberOfRecords = noOfRecords;
		noOfResultsL.setContents("Total Results:" + noOfRecords.toString());
		createPageLinks();
	}

	protected void createPageLinks() {
		List<String> pageValues = new ArrayList<String>();
		if (numberOfPages == null || numberOfPages == 0) {
			numberOfPages = (numberOfRecords.intValue() / pageSize) + 1;
			for (Integer i = 1; i <= numberOfPages; i++) {
				pageValues.add(i.toString());
			}
			goToPage.setValueMap(pageValues.toArray(new String[] {}));
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

	public abstract void viewClicked(Long entityID);

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
		viewClicked(new Long(table.getSelectedRecord().getAttribute("id")));
	}

	@Override
	public void onChanged(ChangedEvent event) {
		if (event.getSource().equals(goToPage)) {
			int pageNumber = 1;
			try {
				pageNumber = new Integer(goToPage.getDisplayValue());
			} catch (NumberFormatException e) {
				SC.say("Invalid Value");
			}
			preFetchTable((pageNumber * pageSize) - pageSize);
		}
	}

}
