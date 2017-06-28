package org.vorstdev.activiti.helper;

import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ernstvorsteveld on 04/04/17.
 */
public abstract class AbstractJavaDelegate {

    public static final Logger logger = LoggerFactory.getLogger(AbstractJavaDelegate.class);

    public static final String EMAIL_ATTRIBUTE_NAME_ATTRIBUTE = "email_attribute_name";
    public static final String DEFAULT_EMAIL_ATTRIBUTE_NAME = "email";

    public String getEmail(DelegateExecution execution) {
        if (logger.isDebugEnabled()) {
            logger.debug("About to retrieve email address.");
        }
        Object emailAttributeNameAttributeObject = execution.getVariable(EMAIL_ATTRIBUTE_NAME_ATTRIBUTE);
        if (emailAttributeNameAttributeObject == null) {
            return getEmailValue(execution, DEFAULT_EMAIL_ATTRIBUTE_NAME);
        } else {
            Object emailAttributeObject = execution.getVariable((String) emailAttributeNameAttributeObject);
            return getEmailValue(execution, emailAttributeObject);
        }

    }

    private String getEmailValue(DelegateExecution execution, Object emailAttributeName) {
        if (emailAttributeName == null) {
            logger.error("No attribute {} found in activiti process instance.", emailAttributeName);
            return null;
        }
        Object emailObject = execution.getVariable((String) emailAttributeName);
        if (emailObject == null) {
            logger.error("No value found for attribute {} in activiti process instance.", emailAttributeName);
            return null;
        }
        return (String) emailObject;
    }
}
