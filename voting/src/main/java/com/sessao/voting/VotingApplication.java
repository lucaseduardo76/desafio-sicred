package com.sessao.voting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class VotingApplication implements CommandLineRunner {

	@Value("${spring.cloud.aws.sns.topic.name}")
	private String teste;
	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(teste);
	}
}
