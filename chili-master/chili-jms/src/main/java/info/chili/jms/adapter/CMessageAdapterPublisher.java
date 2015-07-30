/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.jms.adapter;

import info.chili.spring.SpringContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.stereotype.Service;

/**
 *
 * @author ayalamanchili
 */
@Service("cMessageAdapterPublisher")
public class CMessageAdapterPublisher {

    public void publish(CMessage msg) throws IllegalAccessException {
        try {
            for (String adapterName : SpringContext.getApplicationContext().getBeanNamesForType(Class.forName(msg.getAdapterName()))) {
                Object obj = SpringContext.getBean(adapterName);
                Method method = obj.getClass().getDeclaredMethod("process", msg.getPayload().getClass());
                method.invoke(obj, msg.getPayload());
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static CMessageAdapterPublisher instance() {
        return (CMessageAdapterPublisher) SpringContext.getBean("cMessageAdapterPublisher");
    }
}
