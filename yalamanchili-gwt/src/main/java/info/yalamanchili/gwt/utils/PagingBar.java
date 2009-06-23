package info.yalamanchili.gwt.utils;

import info.yalamanchili.gwt.composite.ALComposite;

import com.google.gwt.user.client.ui.Grid;

public class PagingBar extends ALComposite {
	protected Grid grid = new Grid(1, 2);
	protected int startIndex;
	protected final int size = 10;
	protected ClickableLink next = new ClickableLink("next");
	protected ClickableLink previous = new ClickableLink("previous");

	public PagingBar() {
		init(grid);
	}

	@Override
	protected void addListeners() {

	}

	@Override
	protected void addWidgets() {
	}

	@Override
	protected void configure() {

	}

	public int getStartIndex() {
		return startIndex;
	}

	public void incrementStartIndex() {
		startIndex = startIndex + size;
	}

	public void decrementStartIndex() {
		startIndex = startIndex - size;
	}

	public ClickableLink getNextButton() {
		return next;
	}

	public ClickableLink getPreviousButton() {
		return previous;
	}

	public void addNextButton() {
		grid.setWidget(0, 1, next);
	}

	public void addPreviousButton() {
		grid.setWidget(0, 0, previous);
	}

	public void clear() {
		grid.remove(next);
		grid.remove(previous);
	}
}
