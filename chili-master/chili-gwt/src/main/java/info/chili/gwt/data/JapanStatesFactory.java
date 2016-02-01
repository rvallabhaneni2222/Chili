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
public class JapanStatesFactory {
     public static List<String> getStates() {
        List<String> states = new ArrayList();
        states.add("SELECT");
        states.add("TOY");
        return states;
    }
}
