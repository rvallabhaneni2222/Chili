/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.gwt.crud;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Button;
import info.chili.gwt.composite.BaseFieldWithTextBox;
import info.chili.gwt.fields.DateField;
import info.chili.gwt.fields.TextAreaField;
import info.chili.gwt.utils.Utils;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author ayalamanchili
 */
public abstract class TCreateComposite extends TCRUDComposite implements ClickHandler {

    private Logger logger = Logger.getLogger(CreateComposite.class.getName());

    public enum CreateCompositeType {

        CREATE, ADD
    }
    CreateCompositeType type;

    public TCreateComposite(CreateCompositeType type) {
        this.type = type;
    }
    // TODO get button names from resource bundle
    public Button create = new Button("Create");
    public Button add = new Button("add");

    public void initCreateComposite(String className, final ConstantsWithLookup constants) {
        init(className, false, constants);
        if (CreateCompositeType.CREATE.equals(type)) {
            entityActionsPanel.add(create);
            create.addClickHandler(this);
            create.addStyleName("y-gwt-createB");
        }
        if (CreateCompositeType.ADD.equals(type)) {
            entityActionsPanel.add(add);
            add.addClickHandler(this);
        }

        entityCaptionPanel.addStyleName("y-gwt-CreateEntityCaptionPanel");
        entityFieldsPanel.addStyleName("y-gwt-CreateEntityDisplayWidget");
        basePanel.addStyleName("y-gwt-CreateBasePanel");
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                setFocus();
            }
        });
    }

    protected abstract JSONObject populateEntityFromFields();

    protected abstract void createButtonClicked();

    protected abstract void addButtonClicked();

    protected abstract void postCreateSuccess(String result);

    protected void setFocus() {
        
    }

    @Override
    public void onClick(ClickEvent event) {
        if (event.getSource().equals(create) || event.getSource().equals(add)) {
            entity = populateEntityFromFields();
            if (processClientSideValidations(entity)) {
                if (create.isAttached()) {
                    createButtonClicked();
                }
                if (add.isAttached()) {
                    addButtonClicked();
                }
                disableSubmitButtons();
            }
        }
    }

    @Override
    protected void enterKeyPressed() {
        onClick(null);
    }

    @Override
    protected void enableSubmitButtons() {
        if (create.isAttached()) {
            create.setEnabled(true);
        }
        if (add.isAttached()) {
            add.setEnabled(true);
        }
    }

    @Override
    protected void disableSubmitButtons() {
        if (create.isAttached()) {
            create.setEnabled(false);
        }
        if (add.isAttached()) {
            add.setEnabled(false);
        }
    }

    public JSONObject getPopulatedEntity() {
        return populateEntityFromFields();
    }

    protected void setButtonText(String key) {
        create.setText(Utils.getKeyValue(key, constants));
        add.setText(Utils.getKeyValue(key, constants));
    }

    /**
     * override this method to handle any client side validation before calling
     * the server
     */
    protected boolean processClientSideValidations(JSONObject entity) {
        return true;
    }

    protected void setBackgroundText() {
        for (Map.Entry entry : fields.entrySet()) {
            if (entry.getValue() instanceof BaseFieldWithTextBox) {
                BaseFieldWithTextBox field = (BaseFieldWithTextBox) entry.getValue();
                field.setBackgroundText();
            }
            if (entry.getValue() instanceof TextAreaField) {
                TextAreaField textAreaField = (TextAreaField) entry.getValue();
                textAreaField.setBackgroundText();
                textAreaField.getTextbox().setCharacterWidth(75);
                textAreaField.getTextbox().setVisibleLines(4);
            }
            if (entry.getValue() instanceof DateField) {
                DateField field = (DateField) entry.getValue();
                field.setBackgroundText();
            }
        }
    }
}
