package com.neok.commonSpringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableScheduling
@EnableJpaAuditing
@PropertySources({
	@PropertySource("classpath:/properties/temp.properties"),
})
@SpringBootApplication
public class CommonSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonSpringbootApplication.class, args);
	}

}
