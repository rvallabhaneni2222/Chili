package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;
import info.yalamanchili.gwt.widgets.ImageUploadPanel;

import com.google.gwt.user.client.ui.Image;

public class ImageField extends BaseField {
	ImageUploadPanel imageUploadPanel = new ImageUploadPanel();
	Image image = new Image();

	public ImageField(String labelName, String attributeName, String className,
			Boolean readOnly) {
		super(labelName, attributeName, className, readOnly, false);
		configureAddMainWidget();
	}

	public ImageField(String labelName, String attributeName, String className,
			Boolean readOnly, Boolean isRequired) {
		super(labelName, attributeName, className, readOnly, isRequired);
		configureAddMainWidget();
	}

	public ImageField(String labelName, String attributeName, String className,
			Boolean readOnly, String url, int width, int height) {
		super(labelName, attributeName, className, readOnly, false);
		image.setUrl(url);
		image.setPixelSize(width, height);
		configureAddMainWidget();
	}

	public ImageField(String labelName, String attributeName, String className,
			Boolean readOnly, Boolean isRequired, String url, int width,
			int height) {
		super(labelName, attributeName, className, readOnly, isRequired);
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

	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}
}
