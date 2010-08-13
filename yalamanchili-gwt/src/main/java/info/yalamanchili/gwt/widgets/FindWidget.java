package info.yalamanchili.gwt.widgets;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

public class FindWidget extends Composite {
	final FlowPanel panel = new FlowPanel();
	final Button findB = new Button("Find");
	final TextBox findTB = new TextBox();

	public FindWidget(ClickHandler clickHandler) {
		initWidget(panel);
		panel.add(findTB);
		panel.add(findB);
		findB.addClickHandler(clickHandler);
		findTB.addStyleName("y-gwt-FindWidget-TextBox");
		findTB.addStyleName("y-gwt-Spacing");
		findB.addStyleName("y-gwt-FindWidget-Button");
		findB.addStyleName("y-gwt-Spacing");
		panel.addStyleName("y-gwt-FindWidget-Panel");
		panel.addStyleName("y-gwt-Spacing");
	}

	public Button getFindButton() {
		return findB;
	}

	public TextBox getFindTextBox() {
		return findTB;
	}

	public String getSearchText() {
		return findTB.getText();
	}
}
