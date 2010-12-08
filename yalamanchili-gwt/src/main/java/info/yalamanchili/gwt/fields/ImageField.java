package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;
import info.yalamanchili.gwt.widgets.ImageUploadPanel;

import com.google.gwt.user.client.ui.Image;

public class ImageField extends BaseField {
	ImageUploadPanel imageUploadPanel = new ImageUploadPanel();
	Image image = new Image();

	public ImageField(String labelName, Boolean readOnly) {
		super(labelName, readOnly);
		configureAddMainWidget();
	}

	@Override
	protected void configureAddMainWidget() {
		if (!readOnly) {
			fieldPanel.insert(imageUploadPanel, 0);
		} else {
			fieldPanel.insert(image, 0);
		}
	}

	public String getImageName() {
		return imageUploadPanel.getImageName();
	}

	public void setImage(String url) {
		image.setUrl(url);
	}
}
