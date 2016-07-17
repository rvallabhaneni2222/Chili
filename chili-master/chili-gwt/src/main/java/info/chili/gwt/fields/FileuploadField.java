/**
 * System Soft Technolgies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
package info.chili.gwt.fields;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import info.chili.gwt.composite.ALComposite;
import info.chili.gwt.config.ChiliClientConfig;
import info.chili.gwt.utils.FileUtils;
import info.chili.gwt.utils.JSONUtils;
import info.chili.gwt.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
//TODO extend from BaseField

public abstract class FileuploadField extends ALComposite implements ClickHandler, SubmitHandler, SubmitCompleteHandler {

    private static Logger logger = Logger.getLogger(FileuploadField.class.getName());
    protected String filePrefix;
    protected boolean submitted = false;
    protected boolean required;
    FlowPanel panel = new FlowPanel();
    protected FormPanel formPanel = new FormPanel();
    Label label = new Label("upload");
    FileUpload fileUpload = new FileUpload();
    protected HTML message = new HTML();
    Button submit = new Button("Upload");

    public FileuploadField(ConstantsWithLookup constants, String className, String attributeName, String filePrefix, boolean required) {
        instance = this;
        this.label.setText(Utils.getAttributeLabel(attributeName, className, constants));
        this.required = required;
        this.filePrefix = filePrefix;
        init(formPanel);
        submit.setVisible(false);
    }

    public FileuploadField(ConstantsWithLookup constants, String className, String attributeName, String filePrefix, boolean required, boolean supportMultiple) {
        instance = this;
        this.label.setText(Utils.getAttributeLabel(attributeName, className, constants));
        this.required = required;
        this.filePrefix = filePrefix;
        init(formPanel);
        submit.setVisible(false);
        fileUpload.getElement().setAttribute("multiple", "multiple");
    }

    @Override
    protected void addListeners() {
        submit.addClickHandler(this);
        formPanel.addSubmitHandler(this);
        formPanel.addSubmitCompleteHandler(this);
    }

    @Override
    protected void configure() {
        formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        formPanel.setMethod(FormPanel.METHOD_POST);
        fileUpload.setName(filePrefix);
        formPanel.setAction(getUploadUrl());
    }

    protected String getUploadUrl() {
        return ChiliClientConfig.instance().getFileUploadUrl();
    }

    public void appendUploadUrl(String queryPath) {
        formPanel.setAction(formPanel.getAction() + queryPath);
    }

    @Override
    protected void addWidgets() {
        panel.add(label);
        panel.add(fileUpload);
        panel.add(message);
        panel.add(submit);
        formPanel.add(panel);
    }

    @Override
    public void onClick(ClickEvent arg0) {
        clearMessage();
        if (arg0.getSource().equals(submit)) {
            formPanel.submit();
        }
        formPanel.clear();
    }

    public void upload(String entityId) {
        if (!isEmpty()) {
            clearMessage();
            setEntityId(entityId);
            formPanel.submit();
            formPanel.clear();
        } else {
            onUploadComplete("");
        }
    }

    public void upload(JSONArray fileObjs, String fileUrlFieldName) {
        if (!isEmpty()) {
            clearMessage();
            setEntityIds(fileObjs, fileUrlFieldName);
            formPanel.submit();
            formPanel.clear();
        } else {
            onUploadComplete("");
        }
    }

    public boolean isEmpty() {
        if (fileUpload.getFilename() == null || fileUpload.getFilename().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void clearMessage() {
        message.setHTML("");
    }

    public void setMessage(String text) {
        message.setHTML(text);
    }

    public JSONString getFileName() {
        return new JSONString(filePrefix + "_entityId_" + stripFileName(fileUpload.getFilename()));
    }

    public JSONString getFileName(FileUpload upload) {
        return new JSONString(filePrefix + "_entityId_" + stripFileName(upload.getFilename()));
    }

    public void setEntityId(String entityId) {
        fileUpload.setName(filePrefix + "_" + entityId + "_");
    }

//TODO make this better
    public void setEntityIds(JSONArray fileObjs, String fileUrlFieldName) {
        StringBuilder fileNameB = new StringBuilder();
        int j = 0;
        for (int i = 0; i < fileObjs.size(); i++) {
            JSONObject fileObj = (JSONObject) fileObjs.get(i);
            String entityId = JSONUtils.toString(fileObj, "id");
            String url = JSONUtils.toString(fileObj, fileUrlFieldName) + "&";
            if (getFileNames().get(j).stringValue().replaceAll("[^a-zA-Z0-9\\._]+", "_").contains(FileUtils.getFileNameFromUrl(url))) {
                String name = filePrefix + "_" + entityId + "_";
                fileNameB.append(name).append(";");
                j++;

            }
        }
        fileUpload.setName(fileNameB.toString());
    }

    public List<JSONString> getFileNames() {
        List<JSONString> files = new ArrayList<JSONString>();
        for (String str : getFileNames(fileUpload.getElement()).split(";")) {
            files.add(new JSONString(filePrefix + "_entityId_" + stripFileName(str)));
        }
        return files;
    }

    public static native String getFileNames(Element input) /*-{

    var ret = "";

    //microsoft support
    if (typeof (input.files) == 'undefined'
            || typeof (input.files.length) == 'undefined') {
        return input.value;
    }

    for ( var i = 0; i < input.files.length; i++) {
        if (i > 0) {
            ret += ";";
        }
        ret += input.files[i].name;
    }
    return ret;
}-*/;

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    protected String stripFileName(String fileName) {
        String cleanName;
        if (fileName.lastIndexOf("\\") > 0) {
            cleanName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        } else {
            cleanName = fileName;
        }
        return cleanName.replaceAll("[^a-zA-Z0-9\\._]+", "_");
    }

    private static FileuploadField instance;

    public static FileuploadField instance() {
        return instance;
    }

    public abstract void onUploadComplete(String res);

    @Override
    public void onSubmitComplete(SubmitCompleteEvent event) {
        if (event.getResults().contains("Error")) {
            onFileUploadError(event.getResults());
        }
        onUploadComplete(event.getResults());
    }
    /*
     * override this to change the error message
     */

    protected void onFileUploadError(String err) {
        Window.alert(err);
    }

    @Override
    public void onSubmit(SubmitEvent event) {
        logger.info("on submit");
    }
}
