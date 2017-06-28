package org.vorstdev.activiti.activation;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Created by ernstvorsteveld on 25/10/15.
 */
public class NoopServiceTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("Executing Noop SerivceTask.");
    }
}
