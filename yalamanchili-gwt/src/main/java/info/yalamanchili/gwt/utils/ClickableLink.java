package info.yalamanchili.gwt.utils;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

public class ClickableLink extends Label implements MouseListener {
	private Integer id;
	private Boolean clicked = false;

	public ClickableLink(String name) {
		this.setText(name);
		this.setStyleName("ClickableLink");
		addMouseListener(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void onMouseDown(Widget arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void onMouseEnter(Widget arg0) {
		this.addStyleName("ClickableLink-enter");
	}

	public void onMouseLeave(Widget arg0) {
		this.removeStyleName("ClickableLink-enter");

	}

	public void onMouseMove(Widget arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void onMouseUp(Widget arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public Boolean getClicked() {
		return clicked;
	}

	public void setClicked(Boolean clicked) {
		this.clicked = clicked;
	}

	public void toggle() {
		if (clicked) {
			clicked = false;
		} else {
			clicked = true;
		}
	}
}
