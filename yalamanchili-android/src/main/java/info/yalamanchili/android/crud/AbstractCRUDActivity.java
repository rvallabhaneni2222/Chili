package info.yalamanchili.android.crud;

import info.yalamanchili.android.commons.DateParser;
import info.yalamanchili.android.views.BaseField;
import info.yalamanchili.android.views.BooleanField;
import info.yalamanchili.android.views.DateField;
import info.yalamanchili.android.views.DecimalField;
import info.yalamanchili.android.views.Field;
import info.yalamanchili.android.views.NumericField;
import info.yalamanchili.android.views.StringField;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View.OnClickListener;

public abstract class AbstractCRUDActivity extends Activity implements
		OnClickListener {

	protected Map<String, BaseField> fields = new HashMap<String, BaseField>();

	// TODO
	// assign and add field.
	// populate entity from fields. update/create
	// on click
	// on create
	// url..
	protected void addAndAssignField(int id, String key, String label,
			Field type) {
		switch (type) {
		case STRING_FIELD:
			StringField stringField = (StringField) findViewById(id);
			stringField.setLabel(label);
			fields.put(key, stringField);
			break;
		case NUMERIC_FIELD:
			NumericField numericField = (NumericField) findViewById(id);
			numericField.setLabel(label);
			fields.put(key, numericField);
			break;
		case DECIMAL_FIELD:
			DecimalField deciamlField = (DecimalField) findViewById(id);
			deciamlField.setLabel(label);
			fields.put(key, deciamlField);
			break;
		case BOOLEAN_FIELD:
			BooleanField booleanField = (BooleanField) findViewById(id);
			booleanField.setLabel(label);
			fields.put(key, booleanField);
			break;
		case DATE_FIELD:
			DateField dateField = (DateField) findViewById(id);
			dateField.setLabel(label);
			dateField.setDateFieldId(id);
			fields.put(key, dateField);
			break;
		}
	}

	protected JSONObject populateEntityFromFields(String key, JSONObject entity) {
		JSONObject wrapper = new JSONObject();
		try {
			for (String fieldKey : fields.keySet()) {
				if (fields.get(fieldKey) instanceof StringField) {
					StringField stringField = (StringField) fields
							.get(fieldKey);
					if (!TextUtils.isEmpty(stringField.getValue())) {
						entity.put(fieldKey, stringField.getValue());
					}
					continue;
				}
				if (fields.get(fieldKey) instanceof NumericField) {
					NumericField numericField = (NumericField) fields
							.get(fieldKey);
					if (!TextUtils.isEmpty(numericField.getValue())) {
						entity.put(fieldKey, numericField.getValue());
					}
					continue;
				}
				if (fields.get(fieldKey) instanceof DecimalField) {
					DecimalField deciamlField = (DecimalField) fields
							.get(fieldKey);
					if (!TextUtils.isEmpty(deciamlField.getValue())) {
						entity.put(fieldKey, deciamlField.getValue());
					}
					continue;
				}
				if (fields.get(fieldKey) instanceof BooleanField) {
					BooleanField booleanField = (BooleanField) fields
							.get(fieldKey);
					if (booleanField.getValue() != null) {
						entity.put(fieldKey, booleanField.getValue());
					}
					continue;
				}
				if (fields.get(fieldKey) instanceof DateField) {
					DateField dateField = (DateField) fields.get(fieldKey);
					if (dateField.getValue() != null) {
						entity.put(fieldKey,
								DateParser.toString(dateField.getValue()));
					}
					continue;
				}
			}
			wrapper.put(key, entity);
		} catch (JSONException e) {
			Log.e("y-android", "failure to populateEntityFromFields", e);
			throw new RuntimeException(e);
		}
		return wrapper;
	}

	protected void processValidationErrors(String errorsString) {

		// TODO fix issue with get("Error") is object or array
		try {
			JSONObject errorsObject = new JSONObject(errorsString)
					.getJSONObject("Errors");
			// One Error Object
			if (errorsObject.optJSONObject("Error") != null) {
				JSONObject error = errorsObject.getJSONObject("Error");
				showValidationMessage(error);
			}
			// is Array of Errors
			else {
				JSONArray errors = errorsObject.getJSONArray("Error");
				int i = 0;
				while (!errors.isNull(i)) {
					showValidationMessage(errors.getJSONObject(i));
					i++;
				}

			}
		} catch (Exception e) {
			// TODO navigate back notify user?
			Log.e("y-android", "failure to process validation errors", e);
			throw new RuntimeException(e);
		}
	}

	protected void showValidationMessage(JSONObject error) throws JSONException {
		String description = (String) error.get("Description");
		String source = (String) error.get("Source");
		if (fields.containsKey(source)) {
			((BaseField) fields.get(source)).setErrorMessage(description);
		}
	}
}
