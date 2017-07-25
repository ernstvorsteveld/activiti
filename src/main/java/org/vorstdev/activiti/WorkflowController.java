package org.vorstdev.activiti;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vorstdev.activiti.domain.SimpleTask;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by ernstvorsteveld on 28/06/17.
 */
@RestController
@RequestMapping(value = "/workflow")
public interface WorkflowController {

    /**
     * Start a process and return what the task is after starting.
     *
     * @param processName
     * @param parameters
     * @return
     */
    @RequestMapping(value = "start/{processName}", method = RequestMethod.POST)
    ResponseEntity<SimpleTask> start(
            @RequestParam("processName") final String processName,
            final Map<String, Object> parameters,
            HttpServletRequest request);

    /**
     * A SimpleTask object is one of the parameter entries.
     *
     * @param parameters
     * @return
     */
    @RequestMapping(value = "handle", method = RequestMethod.POST)
    SimpleTask handle(final Map<String, Object> parameters);
}
