/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm.task;

import info.chili.bpm.domain.BPMTaskDelegateRule;
import info.chili.bpm.types.Task;
import org.activiti.engine.delegate.DelegateTask;

/**
 *
 * @author ayalamanchili
 */
public abstract class AbstractTaskDelegate {

    /**
     * takes a Task to find the associated rule and invokes the corresponding
     * class to get the correct assignee id.
     *
     * @param task
     * @param rule
     * @return
     */
    public abstract Task getDelegationInfo(DelegateTask task, BPMTaskDelegateRule rule);

    Object getDelegationInfo(DelegateTask task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
