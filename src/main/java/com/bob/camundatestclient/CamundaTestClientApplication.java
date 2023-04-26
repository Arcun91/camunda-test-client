package com.bob.camundatestclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;

@SpringBootApplication
public class CamundaTestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaTestClientApplication.class, args);


		ZeebeClient client = ZeebeClient.newClientBuilder().gatewayAddress("127.0.0.1:26500").build();
        System.out.println("Connected");
		JobWorker jobWorker = client.newWorker().jobType("testServiceTask") //ssl handshake problem, https?
		.handler((jobClient, job) -> {
                System.out.print("yay");
                jobClient.newCompleteCommand(job.getKey())
                    .send()
                    .join();
            })
        .open();

		//client.close();

	}

}
