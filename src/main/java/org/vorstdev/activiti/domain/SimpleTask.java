package org.vorstdev.activiti.domain;

import org.activiti.engine.task.Task;

/**
 * Created by ernstvorsteveld on 28/06/17.
 */
public abstract class SimpleTask {

    private final String processId;
    private final String processName;
    private Task task;

    protected SimpleTask(String processName, String processId) {
        this.processName = processName;
        this.processId = processId;
    }

    public String getProcessId() {
        return processId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
