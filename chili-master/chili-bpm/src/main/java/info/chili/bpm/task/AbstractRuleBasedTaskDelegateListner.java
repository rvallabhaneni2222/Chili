/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm.task;

import info.chili.bpm.dao.BPMTaskDelegateRuleDao;
import info.chili.bpm.domain.BPMTaskDelegateRule;
import info.chili.spring.SpringContext;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 *
 * @author ayalamanchili
 */
public abstract class AbstractRuleBasedTaskDelegateListner implements TaskListener {

    @Override
    public void notify(DelegateTask task) {
        if ("create".equals(task.getEventName())) {
            processTaskDelegation(task);
        }
        processTask(task);
    }

    public abstract void processTask(DelegateTask task);

    protected void processTaskDelegation(DelegateTask task) {
        String processId = task.getExecution().getProcessDefinitionId().substring(0, task.getExecution().getProcessDefinitionId().indexOf(":"));
        BPMTaskDelegateRule rule = BPMTaskDelegateRuleDao.instance().find(processId, task.getTaskDefinitionKey());
        if (rule != null) {
            AbstractTaskDelegate delegate = (AbstractTaskDelegate) SpringContext.getBean(rule.getRuleName());
            task.setAssignee(delegate.getDelegationInfo(task, rule).getAssignee());
        }
    }

}
