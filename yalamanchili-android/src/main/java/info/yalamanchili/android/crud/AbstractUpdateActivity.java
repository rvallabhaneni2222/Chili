package info.yalamanchili.android.crud;

import info.yalamanchili.android.http.AsyncHttpPut;
import info.yalamanchili.android.views.BooleanField;
import info.yalamanchili.android.views.DateField;
import info.yalamanchili.android.views.DecimalField;
import info.yalamanchili.android.views.Field;
import info.yalamanchili.android.views.NumericField;
import info.yalamanchili.android.views.StringField;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public abstract class AbstractUpdateActivity extends AbstractCRUDActivity {
	protected Button update;
	protected JSONObject entity;
	protected Map<String, Field> fieldTypes = new HashMap<String, Field>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(contentViewId());
		customizeTitle();
		update = (Button) findViewById(updateButtonId());
		update.setOnClickListener(this);
		assignFields();
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d("ichili-android", "in update start");
		populateFieldsFromEntity();
	}

	/* set the create panel id here */
	protected abstract int contentViewId();

	/* set the create button id here */
	protected abstract int updateButtonId();

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

		if (view == update) {
			preUpdate();
			onUpdate();
		}
	}

	/* set the entity from the parent entity is exists */
	/*
	 * eg: if (getIntent().getSerializableExtra("entity") != null) { dealer =
	 * (JSONObject) getIntent() .getSerializableExtra("entity"); } else { dealer
	 * = new JSONObject(); }
	 */
	protected abstract void preUpdate();

	protected void onUpdate() {
		new AsyncHttpPut(this) {
			@Override
			protected void onResponse(String result) {
				Toast.makeText(AbstractUpdateActivity.this, "updated",
						Toast.LENGTH_LONG);
				finish();
			}

			@Override
			protected void onValidationErrors(String errorsString) {
				processValidationErrors(errorsString);
			}
		}.execute(updateURL(), populateEntityFromFields(entityName(), entity)
				.toString());
	}

	// TODO make this generic
	protected abstract String updateURL();

	protected abstract String entityName();

	protected void populateFieldsFromEntity() {
		try {
			entity = new JSONObject(getIntent().getSerializableExtra("entity")
					.toString());
			for (String key : fields.keySet()) {
				if (entity.has(key)) {
					switch (fieldTypes.get(key)) {
					case STRING_FIELD:
						StringField stringField = (StringField) fields.get(key);
						stringField.setValue((String) entity.get(key));
						break;
					case NUMERIC_FIELD:
						NumericField numericField = (NumericField) fields
								.get(key);
						numericField.setValue((String) entity.get(key));
						break;
					case DECIMAL_FIELD:
						DecimalField decimalField = (DecimalField) fields
								.get(key);
						decimalField.setValue((String) entity.get(key));
						break;
					case BOOLEAN_FIELD:
						BooleanField booleanField = (BooleanField) fields
								.get(key);
						if (entity.get(key).toString().equalsIgnoreCase("true")) {
							booleanField.setValue(true);
						} else {
							booleanField.setValue(false);
						}
						break;
					case DATE_FIELD:
						DateField dateField = (DateField) fields.get(key);
						dateField.setValue((String) entity.get(key));
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void addAndAssignField(int id, String key, String label,
			Field type) {
		switch (type) {
		case STRING_FIELD:
			StringField stringField = (StringField) findViewById(id);
			stringField.setLabel(label);
			fields.put(key, stringField);
			fieldTypes.put(key, type);
			break;
		case NUMERIC_FIELD:
			NumericField numericField = (NumericField) findViewById(id);
			numericField.setLabel(label);
			fields.put(key, numericField);
			fieldTypes.put(key, type);
			break;
		case DECIMAL_FIELD:
			DecimalField deciamlField = (DecimalField) findViewById(id);
			deciamlField.setLabel(label);
			fields.put(key, deciamlField);
			fieldTypes.put(key, type);
			break;
		case BOOLEAN_FIELD:
			BooleanField booleanField = (BooleanField) findViewById(id);
			booleanField.setLabel(label);
			fields.put(key, booleanField);
			fieldTypes.put(key, type);
			break;
		case DATE_FIELD:
			DateField dateField = (DateField) findViewById(id);
			dateField.setLabel(label);
			dateField.setDateFieldId(id);
			fields.put(key, dateField);
			fieldTypes.put(key, type);
			break;
		}
	}
}
