/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.gwt.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import java.util.LinkedHashMap;

/**
 *
 * @author ayalamanchili
 */
public abstract class CMenuBar extends Composite implements ClickHandler {

    HorizontalPanel menuP = new HorizontalPanel();
    protected static final String SELECTED_MENU_ITEM_STYLE = "y-gwt-MenuItem-selectedMI";
    protected static final String MENU_ITEM_STYLE = "y-gwt-MenuItem";
    LinkedHashMap<Label, Command> menuItems = new LinkedHashMap();

    public CMenuBar() {
        initWidget(menuP);
        menuP.addStyleName("y-gwt-MenuBar");
        configureMenu();
        addDefaultItemStyle();
    }

    protected abstract void configureMenu();

    protected void addMenuItem(String menuItemId, String menuItemText, Command command) {
        Label mi = new Label(menuItemText);
        mi.getElement().setId(menuItemId);
        mi.setStyleName(MENU_ITEM_STYLE);
        mi.addClickHandler(this);
        menuP.add(mi);
        menuItems.put(mi, command);

    }

    @Override
    public void onClick(ClickEvent event) {
        for (Label mi : menuItems.keySet()) {
            if (event.getSource().equals(mi)) {
                mi.setStyleName(SELECTED_MENU_ITEM_STYLE);
                menuItems.get(mi).execute();
            } else {
                mi.setStyleName(MENU_ITEM_STYLE);
            }
        }
    }

    public void selectDefaultItem() {
        for (Label mi : menuItems.keySet()) {
            menuItems.get(mi).execute();
            break;
        }
    }

    public final void addDefaultItemStyle() {

        for (Label mi : menuItems.keySet()) {
            mi.setStyleName(SELECTED_MENU_ITEM_STYLE);
            break;
        }
    }
}
