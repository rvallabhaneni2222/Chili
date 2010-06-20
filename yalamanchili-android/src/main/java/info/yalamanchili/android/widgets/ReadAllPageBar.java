package info.yalamanchili.android.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReadAllPageBar extends LinearLayout {
	Button createButton;
	Button nextPageButton;
	Button previousPageButton;
	
	public ReadAllPageBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init() {
		setOrientation(LinearLayout.HORIZONTAL);
		createButton = new Button(getContext());
		nextPageButton = new Button(getContext());
		previousPageButton = new Button(getContext());
		createButton.setText("create");
		nextPageButton.setText("next");
		previousPageButton.setText("previous");
		int lHeight = LayoutParams.WRAP_CONTENT;
		int lWidth = LayoutParams.WRAP_CONTENT;
		addView(createButton, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(nextPageButton, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(previousPageButton, new LinearLayout.LayoutParams(lWidth, lHeight));
	}

	public Button getCreateButton() {
		return createButton;
	}

	public Button getNextPageButton() {
		return nextPageButton;
	}

	public Button getPreviousPageButton() {
		return previousPageButton;
	}
	
}
