/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.gwt.crud;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Button;

/**
 *
 * @author phani
 */
public abstract class TReadComposite extends TCRUDComposite {

    protected Button cloneB = new Button("Create Copy");

    protected void initReadComposite(JSONObject entity, String className, final ConstantsWithLookup constants) {
        this.entity = entity;
        init(className, true, constants);
        entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
        entityFieldsPanel.addStyleName("y-gwt-ReadEntityDisplayWidget");
        basePanel.addStyleName("y-gwt-ReadBasePanel");
        populateFieldsFromEntity(entity);
        if (enableClone()) {
            configureClone();
        }
    }

    protected void initReadComposite(String id, String className, final ConstantsWithLookup constants) {
        this.entityId = id;
        init(className, true, constants);
        entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
        entityFieldsPanel.addStyleName("y-gwt-ReadEntityDisplayWidget");
        basePanel.addStyleName("y-gwt-ReadBasePanel");
        loadEntity(entityId);
        if (enableClone()) {
            configureClone();
        }
    }

    protected void initReadComposite(String className, final ConstantsWithLookup constants) {
        init(className, true, constants);
        entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
        entityFieldsPanel.addStyleName("y-gwt-ReadEntityDisplayWidget");
        basePanel.addStyleName("y-gwt-ReadBasePanel");
        loadEntity(null);
        if (enableClone()) {
            configureClone();
        }
    }

    protected void configureClone() {
        entityActionsPanel.add(cloneB);
        cloneB.addStyleName("y-gwt-cloneB");
        cloneB.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                cloneClicked();
            }
        });
    }

    protected boolean enableClone() {
        return false;
    }

    protected void cloneClicked() {

    }

    public abstract void loadEntity(String entityId);

    public abstract void populateFieldsFromEntity(JSONObject entity);

}
