package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;
import info.yalamanchili.gwt.widgets.ImageUploadPanel;

public class ImageField extends BaseField {
	ImageUploadPanel imagePanel = new ImageUploadPanel();

	public ImageField(String labelName, Boolean readOnly) {
		super(labelName, readOnly);
		configureAddMainWidget();
	}

	@Override
	protected void configureAddMainWidget() {
		if (!readOnly) {
			fieldPanel.insert(imagePanel, 0);
		} else {
			// TODO add imageview panel
		}
	}

	public String getImageName() {
		return imagePanel.getImageName();
	}
}
