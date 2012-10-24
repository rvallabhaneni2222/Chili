package info.chili.gwt.fields;

import info.chili.gwt.composite.BaseFieldWithTextBox;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Window;
import java.util.logging.Logger;

public class IntegerField extends BaseFieldWithTextBox {

    private Logger logger = Logger.getLogger(IntegerField.class.getName());

    @UiConstructor
    public IntegerField(ConstantsWithLookup constants, String attributeName, String className, Boolean readOnly, Boolean isRequired) {
        super(constants, attributeName, className, readOnly, isRequired);
    }

    public void setInteger(Integer number) {
        if (number != null) {
            textbox.setText(number.toString());
        }
    }

    public Integer getInteger() {
        Integer number = null;
        if (textbox.getText() == null || textbox.getText().trim().equals("")) {
            return null;
        }
        try {
            number = new Integer(textbox.getText());
        } catch (NumberFormatException nfe) {
            // TODO need to go to error page
            Window.alert("please enter a valid number");
            return null;
        }
        return number;
    }

    @Override
    // TODO add logic to accept backspace as valid
    public void onKeyPress(KeyPressEvent event) {
        //TODO move to super class
        //This is for support for firefox for special characters like enter,tab,etc...
        if (event.getUnicodeCharCode() == 0) {
            return;
        }
        if (!Character.isDigit(event.getCharCode())) {
            setMessage("invalid value");
            textbox.cancelKey();
        } else {
            clearMessage();
        }

    }

    @Override
    public void onKeyUp(KeyUpEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onKeyDown(KeyDownEvent arg0) {
        // TODO Auto-generated method stub
    }
}