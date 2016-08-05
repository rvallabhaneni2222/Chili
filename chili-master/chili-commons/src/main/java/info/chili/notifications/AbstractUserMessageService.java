/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import java.util.List;

/**
 *
 * @author ayalamanchili
 */
public abstract class AbstractUserMessageService {

    public abstract List<UserMessage> getMessages(String userId);
}
