package info.yalamanchili.gwt.widgets;

import info.yalamanchili.gwt.composite.ALComposite;

import com.google.gwt.user.client.ui.Grid;

// TODO: Auto-generated Javadoc
/**
 * The Class PagingBar.
 */
public class PagingBar extends ALComposite {
	
	/** The grid. */
	protected Grid grid = new Grid(1, 2);
	
	/** The start index. */
	protected int startIndex;
	
	/** The size. */
	protected final int size = 10;
	
	/** The next. */
	protected ClickableLink next = new ClickableLink("next");
	
	/** The previous. */
	protected ClickableLink previous = new ClickableLink("previous");

	/**
	 * Instantiates a new paging bar.
	 */
	public PagingBar() {
		init(grid);
	}

	/* (non-Javadoc)
	 * @see info.yalamanchili.gwt.composite.ALComposite#addListeners()
	 */
	@Override
	protected void addListeners() {

	}

	/* (non-Javadoc)
	 * @see info.yalamanchili.gwt.composite.ALComposite#addWidgets()
	 */
	@Override
	protected void addWidgets() {
	}

	/* (non-Javadoc)
	 * @see info.yalamanchili.gwt.composite.ALComposite#configure()
	 */
	@Override
	protected void configure() {

	}

	/**
	 * Gets the start index.
	 * 
	 * @return the start index
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * Increment start index.
	 */
	public void incrementStartIndex() {
		startIndex = startIndex + size;
	}

	/**
	 * Decrement start index.
	 */
	public void decrementStartIndex() {
		startIndex = startIndex - size;
	}

	/**
	 * Gets the next button.
	 * 
	 * @return the next button
	 */
	public ClickableLink getNextButton() {
		return next;
	}

	/**
	 * Gets the previous button.
	 * 
	 * @return the previous button
	 */
	public ClickableLink getPreviousButton() {
		return previous;
	}

	/**
	 * Adds the next button.
	 */
	public void addNextButton() {
		grid.setWidget(0, 1, next);
	}

	/**
	 * Adds the previous button.
	 */
	public void addPreviousButton() {
		grid.setWidget(0, 0, previous);
	}

	/**
	 * Clear.
	 */
	public void clear() {
		grid.remove(next);
		grid.remove(previous);
	}
}
