package org.vorstdev.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ernstvorsteveld on 23/10/15.
 */
@RestController
@RequestMapping(value = "/process")
public class ActivitiController {

    public static final String ONE_TASK_PROCESS = "registration_process";

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value="start", method= RequestMethod.POST)
    public String startProcessInstance() {
        return runtimeService.startProcessInstanceById(ONE_TASK_PROCESS).getDeploymentId();
    }

    @RequestMapping(value="count", method = RequestMethod.GET)
    public long countProcessInstances() {
        return taskService.createTaskQuery().count();
    }
}
