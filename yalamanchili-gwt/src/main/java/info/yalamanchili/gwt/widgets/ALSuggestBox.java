package info.yalamanchili.gwt.widgets;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ALSuggestBox extends Composite {
	VerticalPanel panel = new VerticalPanel();
	Label label = new Label();
	MultiWordSuggestOracle data = new MultiWordSuggestOracle();
	SuggestBox box = new SuggestBox(data);

	public ALSuggestBox(String name) {
		initWidget(panel);
		label.setText(name);
		panel.add(label);
		panel.add(box);
	}

	public void loadData(List<String> inputs) {
		data.addAll(inputs);
	}

	public String getText() {
		return box.getText();
	}
}
