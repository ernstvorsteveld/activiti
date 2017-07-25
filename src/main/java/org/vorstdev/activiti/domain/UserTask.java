package org.vorstdev.activiti.domain;

import org.activiti.engine.task.Task;

/**
 * Created by ernstvorsteveld on 28/06/17.
 */
public class UserTask extends SimpleTask {

    public UserTask(String processName, String processId) {
        super(processName, processId);
    }

    public static class UserTaskBuilder {

        private final String processName;
        private final String processId;
        private Task task;

        public UserTaskBuilder(String processName, String processId) {
            this.processName = processName;
            this.processId = processId;
        }

        public UserTaskBuilder withTask(Task task) {
            this.task = task;
            return this;
        }

        public UserTask build() {
            UserTask userTask = new UserTask(processName, processId);
            userTask.setTask(task);
            return userTask;
        }
    }
}
