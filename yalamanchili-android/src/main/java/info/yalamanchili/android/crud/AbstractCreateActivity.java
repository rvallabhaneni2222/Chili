package info.yalamanchili.android.crud;

import info.yalamanchili.android.http.AsyncHttpPut;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public abstract class AbstractCreateActivity extends AbstractCRUDActivity {
	protected Button create;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(contentViewId());
		customizeTitle();
		create = (Button) findViewById(createButtonId());
		create.setOnClickListener(this);
		assignFields();
	}

	/* set the create panel id here */
	protected abstract int contentViewId();

	/* set the create button id here */
	protected abstract int createButtonId();

	protected abstract void assignFields();

	// TODO move to parent
	public void customizeTitle() {
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, appTitleBarId());
		TextView mytitletext = (TextView) findViewById(appTitleBArTextViewId());
		mytitletext.setText(titleText());
	}

	/* set the custom title bar */
	// TODO can be defined from main of welcome class
	protected abstract int appTitleBarId();

	protected abstract int appTitleBArTextViewId();

	/* set the custom title text */
	protected abstract String titleText();

	public void onClick(View view) {

		if (view == create) {
			preCreate();
			onCreate();
		}
	}

	/* set the entity from the parent entity is exists */
	/*
	 * eg: if (getIntent().getSerializableExtra("entity") != null) { dealer =
	 * (JSONObject) getIntent() .getSerializableExtra("entity"); } else { dealer
	 * = new JSONObject(); }
	 */
	protected abstract void preCreate();

	protected void onCreate() {
		new AsyncHttpPut(this) {
			@Override
			protected void onResponse(String result) {
				Toast.makeText(AbstractCreateActivity.this, "created",
						Toast.LENGTH_LONG);
				finish();
			}

			@Override
			protected void onValidationErrors(String errorsString) {
				processValidationErrors(errorsString);
			}
		}.execute(createURL(), populateEntityFromFields(entityName(), entity)
				.toString());
	}

	// TODO make this generic
	protected abstract String createURL();

	protected abstract String entityName();
}
