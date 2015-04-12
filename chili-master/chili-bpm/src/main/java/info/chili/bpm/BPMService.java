/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm;

import info.chili.spring.SpringContext;
import java.util.Map;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 */
@Component
public class BPMService {

    @Autowired
    protected RuntimeService bpmRuntimeService;

    public String startProcess(String processKey, Map<String, Object> variables) {
        ProcessInstance processInstance = bpmRuntimeService.startProcessInstanceByKey(processKey, variables);
        return processInstance.getProcessInstanceId();
    }

    public static BPMService instance() {
        return (BPMService) SpringContext.getBean("BPMService");
    }
}
