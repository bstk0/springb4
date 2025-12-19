package br.com.dbengine.springb4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

//@SpringBootApplication
@PropertySource("classpath:sec/secconfig.properties")
@SpringBootApplication  //(scanBasePackages = "br.com.dbengine.springb4")
@ComponentScan("br.com.dbengine.springb4")
public class Springb4Application {

	public static void main(String[] args) {

		SpringApplication.run(Springb4Application.class, args);
	}

}
