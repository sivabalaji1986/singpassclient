package com.example.singpassclient;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SingpassclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingpassclientApplication.class, args);
	}

	@Bean
	CommandLineRunner runClient() {
		return args -> {
			try{
				WebClient client = WebClient.create("https://id.singpass.gov.sg/.well-known/keys");

				client.getHeaders().remove("Accept");
				client.accept("application/json");
				System.out.println("Headers: "+client.getHeaders());

				client.getConfiguration().getInInterceptors().add(new LoggingInInterceptor());
				client.getConfiguration().getOutInterceptors().add(new LoggingOutInterceptor());

				String response = client.get(String.class);
				System.out.println("Response from Singpass: "+response);
			} catch(Exception exception){
				exception.printStackTrace();
			}
		};

	}

}
