package org.vorstdev.activiti.activation;

import org.vorstdev.activiti.domain.SimpleTask;

import java.util.Map;
import java.util.Optional;

/**
 * Created by ernstvorsteveld on 29/06/17.
 */
public interface WorkflowService {

    Optional<SimpleTask> start(String processName, Map<String, Object> parameters);
}
