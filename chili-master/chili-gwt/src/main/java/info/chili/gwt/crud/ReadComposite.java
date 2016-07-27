/**
 * System Soft Technolgies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
package info.chili.gwt.crud;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public abstract class ReadComposite extends CRUDComposite {

    protected Button cloneB = new Button("Create Copy");
    Anchor backL = new Anchor("Back");

    protected void initReadComposite(JSONObject entity, String className, final ConstantsWithLookup constants) {
        this.entity = entity;
        init(className, true, constants);
        configureRead();
        populateFieldsFromEntity(entity);
    }

    protected void initReadComposite(String id, String className, final ConstantsWithLookup constants) {
        this.entityId = id;
        init(className, true, constants);
        configureRead();
        loadEntity(entityId);
    }

    protected void initReadComposite(String className, final ConstantsWithLookup constants) {
        init(className, true, constants);
        configureRead();
        loadEntity(null);
    }

    protected void configureRead() {
        entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
        entityFieldsPanel.addStyleName("y-gwt-ReadEntityDisplayWidget");
        basePanel.addStyleName("y-gwt-ReadBasePanel");
        if (enableClone()) {
            configureClone();
        }
        if (enableBack()) {
            entityFieldsPanel.add(backL);
            backL.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (getReadAllPanel() != null) {
                        Widget parent = ReadComposite.this.getParent();
                        ReadComposite.this.removeFromParent();
                        ((Panel) parent).add(getReadAllPanel());
                    }
                }
            });
        }
    }

    @Override
    protected void addWidgetsBeforeCaptionPanel() {
        if (enableBack()) {
            basePanel.add(backL);
        }
    }

    protected boolean enableBack() {
        return false;
    }
    
    protected ReadAllComposite getReadAllPanel() {
        return null;
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
