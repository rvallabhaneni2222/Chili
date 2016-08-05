/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ayalamanchili
 */
@Service
public class RelesenotesMessageService extends AbstractUserMessageService {

    @Override
    public List<UserMessage> getMessages(String userId) {
        return Collections.EMPTY_LIST;
    }
}
