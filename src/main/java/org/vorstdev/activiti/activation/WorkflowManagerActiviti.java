package org.vorstdev.activiti.activation;

import org.activiti.engine.RepositoryService;

import java.net.URI;

/**
 * Created by ernstvorsteveld on 30/06/17.
 */
public class WorkflowManagerActiviti implements WorkflowManager {

    private final RepositoryService repositoryService;

    public WorkflowManagerActiviti(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public void addWorkflow(URI uri) {
        repositoryService.
    }
}
