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
                Class cls = Class.forName(adapterName);
                Object obj = cls.newInstance();
                Method method = cls.getDeclaredMethod("process", msg.getPayload().getClass());
                method.invoke(obj, msg.getPayload());
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static CMessageAdapterPublisher instance() {
        return (CMessageAdapterPublisher) SpringContext.getBean("cMessageAdapterPublisher");
    }
}
