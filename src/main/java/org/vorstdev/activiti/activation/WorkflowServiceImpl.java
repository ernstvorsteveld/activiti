package org.vorstdev.activiti.activation;

import com.google.common.collect.Maps;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vorstdev.activiti.domain.SimpleTask;
import org.vorstdev.activiti.domain.UserTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by ernstvorsteveld on 25/10/15.
 */
@Service
public class WorkflowServiceImpl extends AbstractWorkflowService implements WorkflowService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public Optional<SimpleTask> start(String processName, Map<String, Object> parameters) {
        try {
            Map<String, Object> variables = createVariablesMap(parameters);
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName, variables);
            Task task = taskService.createTaskQuery()
                    .processInstanceId(processInstance.getId())
                    .includeProcessVariables()
                    .active()
                    .singleResult();

            UserTask userTask = new UserTask.UserTaskBuilder(processName, processInstance.getId())
                    .withTask(task)
                    .build();
            return Optional.of(userTask);
        } catch (ActivitiObjectNotFoundException e) {
            logger.error("Could not start process with name {}.", processName);
            return Optional.empty();
        }
    }

    /**
     * Start an new process instance.
     *
     * @param processName
     * @param segment
     * @param brand
     * @param locale
     * @param attributes
     * @return
     */
    public String start(String processName, String segment, String brand, String locale,
                        Map<String, Object> attributes) {
        Map<String, Object> variables = createVariablesMap(segment, brand, locale, attributes);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName, variables);
        return processInstance.getId();
    }

    public Map<String, Object> queryProcess(String processInstanceId) {
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .includeProcessVariables()
                .active()
                .singleResult();
        if (task == null) {
            logger.error("Tried to find the active task for process with id {}.", processInstanceId);
            return Maps.newHashMap();
        }
        String id = task.getId();
        String taskName = task.getName();
        String taskType = task.getTaskDefinitionKey();
        HashMap<String, Object> processData = Maps.newHashMap();
        processData.put(TaskAttributes.taskId.name(), id);
        processData.put(TaskAttributes.taskName.name(), taskName);
        processData.put(TaskAttributes.taskDefinitionKey.name(), taskType);
        processData.put(TaskAttributes.data.name(), task.getProcessVariables());

        return processData;
    }

    public void done(String processInstanceId, String taskId, Map<String, Object> formAttributes) {
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .active()
                .singleResult();
        if (task == null) {
            logger.error("Tried to find the active task for process with id {}.", processInstanceId);
        }
        taskService.complete(taskId, formAttributes);
    }

}
