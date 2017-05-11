/**
 * System Soft Technolgies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
package info.chili.gwt.crud;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import info.chili.gwt.resources.ChiliImages;
import info.chili.gwt.widgets.ClickableImage;
import info.chili.gwt.widgets.GenericPopup;

public class TableRowOptionsWidget extends GenericTableRowOptionsWidget implements MouseOverHandler, MouseOutHandler {

    ClickableImage readLink = new ClickableImage("View", ChiliImages.INSTANCE.viewIcon_16_16());
    ClickableImage updateLink = new ClickableImage("Update", ChiliImages.INSTANCE.updateIcon_16_16());
    ClickableImage deleteLink = new ClickableImage("Delete", ChiliImages.INSTANCE.deleteIcon_16_16());
    ClickableImage printLink = new ClickableImage("Print", ChiliImages.INSTANCE.printIcon_16_16());
    ClickableImage copyLink = new ClickableImage("Copy", ChiliImages.INSTANCE.copyIcon_16_16());
    ClickableImage cancelLink = new ClickableImage("Cancel", ChiliImages.INSTANCE.cancelIcon_16_16());
    ClickableImage markAsReadLink = new ClickableImage("Mark As Read", ChiliImages.INSTANCE.markAsRead_Icon());
    ClickableImage quickViewLink;

    public enum OptionsType {

        READ, UPDATE, DELETE, PRINT, COPY, CANCEL, READ_UPDATE, READ_UPDATE_DELETE, READ_DELETE, UPDATE_DELETE, MARK_READ
    };
    protected OptionsType[] types;

    public TableRowOptionsWidget(OptionsType type, String id) {
        super(id);
        this.types = new OptionsType[1];
        this.types[0] = type;
        addWidgets();
        configure();
        addListeners();
    }

    public TableRowOptionsWidget(String id, OptionsType... types) {
        super(id);
        this.types = types;
        addWidgets();
        configure();
        addListeners();
    }

    protected void addListeners() {

    }

    protected void initListeners(ClickHandler handler) {
        readLink.addClickHandler(handler);
        updateLink.addClickHandler(handler);
        deleteLink.addClickHandler(handler);
        printLink.addClickHandler(handler);
        copyLink.addClickHandler(handler);
        cancelLink.addClickHandler(handler);
        markAsReadLink.addClickHandler(handler);
    }

    protected void configure() {
        readLink.addStyleName("readL");
        updateLink.addStyleName("updateLink");
        deleteLink.addStyleName("deleteLink");
        printLink.addStyleName("printLink");
        copyLink.addStyleName("copyLink");
        cancelLink.addStyleName("cancelLink");
        markAsReadLink.addStyleName("markAsReadLink");
    }

    protected void configureQuickViewLink() {
        quickViewLink = new ClickableImage("quick view", ChiliImages.INSTANCE.quickViewIcon_16_16());
        quickViewLink.addMouseOverHandler(this);
        quickViewLink.addMouseOutHandler(this);
        panel.insert(quickViewLink, 0);
    }

    protected void addWidgets() {
        if (types.length == 1) {
            if (OptionsType.READ.equals(types[0])) {
                panel.add(readLink);
            }
            if (OptionsType.UPDATE.equals(types[0])) {
                panel.add(updateLink);
            }
            if (OptionsType.DELETE.equals(types[0])) {
                panel.add(deleteLink);
            }
            if (OptionsType.READ_UPDATE.equals(types[0])) {
                panel.add(readLink);
                panel.add(updateLink);
            }
            if (OptionsType.READ_UPDATE_DELETE.equals(types[0])) {
                panel.add(readLink);
                panel.add(updateLink);
                panel.add(deleteLink);
            }
            if (OptionsType.READ_DELETE.equals(types[0])) {
                panel.add(readLink);
                panel.add(deleteLink);
            }
            if (OptionsType.UPDATE_DELETE.equals(types[0])) {
                panel.add(updateLink);
                panel.add(deleteLink);
            }
        } else {
            for (OptionsType type : types) {
                if (OptionsType.READ.equals(type)) {
                    panel.add(readLink);
                }
                if (OptionsType.UPDATE.equals(type)) {
                    panel.add(updateLink);
                }
                if (OptionsType.DELETE.equals(type)) {
                    panel.add(deleteLink);
                }
                if (OptionsType.PRINT.equals(type)) {
                    panel.add(printLink);
                }
                if (OptionsType.COPY.equals(type)) {
                    panel.add(copyLink);
                }
                if (OptionsType.CANCEL.equals(type)) {
                    panel.add(cancelLink);
                }
                if (OptionsType.MARK_READ.equals(type)) {
                    panel.add(markAsReadLink);
                }

            }
        }
    }

    public ClickableImage getReadLink() {
        return readLink;
    }

    public ClickableImage getUpdateLink() {
        return updateLink;
    }

    public ClickableImage getDeleteLink() {
        return deleteLink;
    }

    public ClickableImage getPrintLink() {
        return printLink;
    }

    public ClickableImage getCopyLink() {
        return copyLink;
    }

    public ClickableImage getCancelLink() {
        return cancelLink;
    }

    public ClickableImage getMarkAsReadLink() {
        return markAsReadLink;
    }

    @Override
    public void onMouseOver(MouseOverEvent event) {
        if (event.getSource().equals(quickViewLink)) {
            onQuickView();
        }
    }

    @Override
    public void onMouseOut(MouseOutEvent event) {
        if (GenericPopup.instance() != null) {
            GenericPopup.instance().hide();
        }
    }

    protected void onQuickView() {
    }
}
