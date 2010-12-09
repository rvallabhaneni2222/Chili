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

	public ImageField(String labelName, Boolean readOnly, String url,
			int width, int height) {
		super(labelName, readOnly);
		image.setUrl(url);
		image.setPixelSize(width, height);
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
