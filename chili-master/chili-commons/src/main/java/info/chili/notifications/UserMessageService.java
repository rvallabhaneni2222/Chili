/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import info.chili.spring.SpringContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ayalamanchili
 */
@Service
public class UserMessageService {

    public List<UserMessage> getMessages() {
        List<UserMessage> messages = new ArrayList<>();
        try {
            for (String adapterName : SpringContext.getApplicationContext().getBeanNamesForType(AbstractUserMessageService.class)) {
                Object obj = SpringContext.getBean(adapterName);
                Method method = obj.getClass().getDeclaredMethod("getMessages");
                messages.addAll((List<UserMessage>) method.invoke(obj));
            }
        } catch (IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
        return messages;
    }
}
