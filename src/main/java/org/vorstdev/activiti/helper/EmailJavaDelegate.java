package org.vorstdev.activiti.helper;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ernstvorsteveld on 04/04/17.
 */
public class EmailJavaDelegate extends AbstractJavaDelegate implements JavaDelegate {

    private static final Logger logger = LoggerFactory.getLogger(EmailJavaDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {
        String email = super.getEmail(execution);
        executeEmail(email);
    }

    private void executeEmail(String email) {
        logger.info("Sending email to {}.", email);
    }
}
