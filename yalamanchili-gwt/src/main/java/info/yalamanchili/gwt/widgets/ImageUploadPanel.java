package info.yalamanchili.gwt.widgets;

import info.yalamanchili.gwt.composite.ALComposite;
import info.yalamanchili.gwt.widgets.ClickableLink;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;

public class ImageUploadPanel extends ALComposite implements ClickHandler {
	private Logger logger = Logger.getLogger(ImageUploadPanel.class.getName());
	VerticalPanel panel = new VerticalPanel();
	FormPanel formPanel = new FormPanel();
	Label label = new Label("Upload images");
	FileUpload fileUpload = new FileUpload();
	Button submit = new Button("Upload");
	ClickableLink addImage = new ClickableLink("add more..");

	public ImageUploadPanel() {
		init(formPanel);
	}

	@Override
	protected void addListeners() {
		submit.addClickHandler(this);
		addImage.addClickHandler(this);
	}

	@Override
	protected void addWidgets() {
		panel.add(label);
		panel.add(fileUpload);
		panel.add(submit);
		panel.add(addImage);
		formPanel.add(panel);
	}

	@Override
	protected void configure() {
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);
		fileUpload.setName("Upload Image");
		logger.info(GWT.getModuleBaseURL());
		logger.info(GWT.getHostPageBaseURL());
		formPanel.setAction(GWT.getModuleBaseURL() + "imageupload");

		formPanel.addSubmitHandler(new FormPanel.SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				logger.info(formPanel.getAction());
			}
		});
		formPanel
				.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
					public void onSubmitComplete(SubmitCompleteEvent event) {

						Window.alert(event.getResults());
					}
				});
	}

	public void onClick(ClickEvent event) {
		if (event.getSource() == submit) {
			String imagename = fileUpload.getFilename();
			formPanel.submit();
		}
		if (event.getSource() == addImage) {

		}
	}

}
