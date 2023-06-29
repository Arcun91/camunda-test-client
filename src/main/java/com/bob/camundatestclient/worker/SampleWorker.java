package com.bob.camundatestclient.worker;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class SampleWorker {

    @Autowired
    private ZeebeClient zeebeClient;
    

    @JobWorker(type="sample-worker", autoComplete = false)
    public void handleWorker(final JobClient client, final ActivatedJob job){
        System.out.print("sample-worker executed");
        Map<String, Object> variables = new HashMap<>();
        variables.put("test", "navigated worker");
        client.newCompleteCommand(job).variables(variables).send().join();
    }

}
