package org.vorstdev.activiti.activation;

import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vorstdev.activiti.ActivitiApplication;
import org.vorstdev.activiti.ActivitiController;

import java.util.Map;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;

/**
 * Created by ernstvorsteveld on 25/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ActivitiApplication.class})
public class WorkflowServiceImplTest {

    @Autowired
    private WorkflowServiceImpl activationService;

    private String userId;

    @Before
    public void initialise() {
        this.userId = UUID.randomUUID().toString();
    }

    @Test
    public void should_start_activation_process_for_user() {
        String segment = "iwelcome";
        String brand = "engineering";
        String locale = "en_GB";
        Map<String, Object> attributes = Maps.newHashMap();
        attributes.put(userId, "100");
        String start = activationService.start(
                ActivitiController.ONE_TASK_PROCESS, segment, brand, locale, attributes);
        assertThat(start, is(notNullValue()));
        System.out.println("Process instance: " + start);

        Map<String, Object> taskAttributes = activationService.queryProcess(start);
        assertThat(taskAttributes.get(TaskAttributes.taskName.name()), is("form_registration_step_1"));
        assertThat(taskAttributes.get(TaskAttributes.taskDefinitionKey.name()), is("form_registration_utask_1"));

        Map<String,Object> formAttributes = Maps.newHashMap();
        formAttributes.put("email", "engineer@iwelcome.com");
        activationService.done(start, (String) taskAttributes.get(TaskAttributes.taskId.name()), formAttributes);
        taskAttributes = activationService.queryProcess(start);
        assertTrue(((Map<String,Object>) taskAttributes.get(TaskAttributes.data.name())).containsKey("email"));
        formAttributes.clear();
        formAttributes.put("lastname", "Engineer");
        activationService.done(start, (String) taskAttributes.get(TaskAttributes.taskId.name()), formAttributes);
    }

}