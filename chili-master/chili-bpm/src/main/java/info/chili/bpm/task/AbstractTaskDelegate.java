/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm.task;

import org.activiti.engine.delegate.DelegateTask;

/**
 *
 * @author ayalamanchili
 */
public abstract class AbstractTaskDelegate {

    public abstract String getAssignee(DelegateTask task);
}
