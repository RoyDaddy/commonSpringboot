package com.neok.commonSpringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@PropertySources({
	@PropertySource("classpath:/properties/temp.properties"),
})
@SpringBootApplication
public class CommonSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonSpringbootApplication.class, args);
	}

}
