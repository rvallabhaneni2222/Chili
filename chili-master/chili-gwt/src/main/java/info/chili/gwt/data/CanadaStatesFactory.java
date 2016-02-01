/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.gwt.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ayalamanchili
 */
public class CanadaStatesFactory {

    public static List<String> getStates() {
        List<String> states = new ArrayList();
        states.add("SELECT");
        states.add("ON");
        states.add("QC");
        states.add("NS");
        states.add("NB");
        states.add("MB");
        states.add("BC");
        states.add("PE");
        states.add("SK");
        states.add("AB");
        states.add("NL");
        return states;
    }
}
