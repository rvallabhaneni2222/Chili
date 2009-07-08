package info.yalamanchili.gwt.widgets;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc
/**
 * The Class ClickableLink.
 */
public class ClickableLink extends Label implements MouseListener {
	
	/** The id. */
	private Integer id;
	
	/** The clicked. */
	private Boolean clicked = false;

	/**
	 * Instantiates a new clickable link.
	 * 
	 * @param name the name
	 */
	public ClickableLink(String name) {
		this.setText(name);
		this.addStyleName("ClickableLink");
		addMouseListener(this);
	}

	/**
	 * Instantiates a new clickable link.
	 * 
	 * @param name the name
	 * @param isLabel the is label
	 */
	public ClickableLink(String name, Boolean isLabel) {
		this.setText(name);
		this.addStyleName("ClickableLink");
		if (isLabel)
			this.addStyleName("LabelLink");
		addMouseListener(this);
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseDown(com.google.gwt.user.client.ui.Widget, int, int)
	 */
	public void onMouseDown(Widget arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseEnter(com.google.gwt.user.client.ui.Widget)
	 */
	public void onMouseEnter(Widget arg0) {
		this.addStyleName("ClickableLink-enter");
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseLeave(com.google.gwt.user.client.ui.Widget)
	 */
	public void onMouseLeave(Widget arg0) {
		this.removeStyleName("ClickableLink-enter");

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseMove(com.google.gwt.user.client.ui.Widget, int, int)
	 */
	public void onMouseMove(Widget arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseUp(com.google.gwt.user.client.ui.Widget, int, int)
	 */
	public void onMouseUp(Widget arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gets the clicked.
	 * 
	 * @return the clicked
	 */
	public Boolean getClicked() {
		return clicked;
	}

	/**
	 * Sets the clicked.
	 * 
	 * @param clicked the new clicked
	 */
	public void setClicked(Boolean clicked) {
		this.clicked = clicked;
	}

	/**
	 * Toggle.
	 */
	public void toggle() {
		if (clicked) {
			clicked = false;
		} else {
			clicked = true;
		}
	}
}
