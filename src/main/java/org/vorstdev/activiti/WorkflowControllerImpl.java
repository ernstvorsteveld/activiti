package org.vorstdev.activiti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.vorstdev.activiti.activation.WorkflowService;
import org.vorstdev.activiti.domain.SimpleTask;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * Created by ernstvorsteveld on 28/06/17.
 */
public class WorkflowControllerImpl implements WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @Override
    public ResponseEntity<SimpleTask> start(
            @RequestParam("processName") final String processName,
            final Map<String, Object> parameters,
            HttpServletRequest request) {
        UserContext.setHeaders(request);
        Optional<SimpleTask> simpleTaskOptional = workflowService.start(processName, parameters);
        if (simpleTaskOptional.isPresent()) {
            return ResponseEntity.ok(simpleTaskOptional.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public SimpleTask handle(final Map<String, Object> parameters) {
        return null;
    }
}
