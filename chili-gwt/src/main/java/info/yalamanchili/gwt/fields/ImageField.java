package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;
import info.yalamanchili.gwt.widgets.ImageUploadPanel;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Image;

public class ImageField extends BaseField {
	ImageUploadPanel imageUploadPanel = new ImageUploadPanel();
	Image image = new Image();

	@UiConstructor
	@Deprecated
	public ImageField(String labelName, String attributeName, String className, Boolean readOnly) {
		super(labelName, attributeName, className, readOnly, false);
		configureAddMainWidget();
	}

	@UiConstructor
	public ImageField(String labelName, String attributeName, String className, Boolean readOnly, Boolean isRequired) {
		super(labelName, attributeName, className, readOnly, isRequired);
		configureAddMainWidget();
	}

	@UiConstructor
	@Deprecated
	public ImageField(String labelName, String attributeName, String className, Boolean readOnly, String url,
			int width, int height) {
		super(labelName, attributeName, className, readOnly, false);
		image.setUrl(url);
		image.setPixelSize(width, height);
		configureAddMainWidget();
	}

	@UiConstructor
	@Deprecated
	public ImageField(String labelName, String attributeName, String className, Boolean readOnly, Boolean isRequired,
			String url, int width, int height) {
		super(labelName, attributeName, className, readOnly, isRequired);
		image.setUrl(url);
		image.setPixelSize(width, height);
		configureAddMainWidget();
	}

	@UiConstructor
	public ImageField(String labelName, String attributeName, String className, Boolean readOnly, Boolean isRequired,
			String url, String width, String height) {
		super(labelName, attributeName, className, readOnly, isRequired);
		setPixelSize(width, height);
		image.setUrl(url);

		configureAddMainWidget();
	}

	public void setPixelSize(String width, String height) {
		image.setPixelSize(Integer.parseInt(width), Integer.parseInt(height));
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
