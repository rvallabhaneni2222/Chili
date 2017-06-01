/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.gwt.crud;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.HTMLTable;

/**
 *
 * @author Ramana.Lukalapu
 */
public abstract class ReadAllMatrixWidgetComposite extends ReadAllComposite<TableRowOptionsWidget> {

    @Override
    protected void initTable(String className, ConstantsWithLookup constants) {
        this.classCanonicalName = className;
        this.constants = constants;
        init(basePanel);
        preFetchData();
        if (enableDrafts()) {
            addDraftWidgets();
        }
        mainPanel.remove(pagingPanel);
        captionPanel.setStyleName("readAllWidgetCompositeCaptionPanel");
    }

    @Override
    protected void addRowStyles(int size) {
        HTMLTable.RowFormatter rf = table.getRowFormatter();
        captionPanel.setCaptionHTML(classCanonicalName + " (" + size + ")");
        for (int row = 1; row <= size; ++row) {
            rf.addStyleName(row, "y-gwt-ReadAllComposite-Row");
            rf.addStyleName(row, "y-gwt-ReadAllComposite-OddRow");
        }
    }

    @Override
    protected Integer getPageSize() {
        return 5000;
    }

}
