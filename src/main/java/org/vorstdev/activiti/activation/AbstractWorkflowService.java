package org.vorstdev.activiti.activation;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ernstvorsteveld on 04/04/17.
 */
public class AbstractWorkflowService {

    public static final Logger logger = LoggerFactory.getLogger(AbstractWorkflowService.class);

    protected Map<String, Object> createVariablesMap(
            String segment, String brand, String locale,
            Map<String, Object> attributes) {
        assertNotContainsStandardAttributes(attributes);
        Map<String, Object> variables = Maps.newHashMap();
        variables.put(ProcessInstanceAttributes.segment.name(), segment);
        variables.put(ProcessInstanceAttributes.brand.name(), brand);
        variables.put(ProcessInstanceAttributes.locale.name(), locale);
        variables.putAll(attributes);
        return variables;
    }

    private void assertNotContainsStandardAttributes(Map<String, Object> attributes) {
        boolean notContains = true;
        String errorName = "";

        for (ProcessInstanceAttributes current : ProcessInstanceAttributes.values()) {
            if (attributes.containsKey(current.name())) {
                notContains = false;
                errorName = current.name();
                break;
            }
        }

        if (!notContains) {
            logger.error("Attributes contain values for standard attribute names, error: {}", errorName);
            logger.error("You are overriding the values from the request.");
        }
    }

}
