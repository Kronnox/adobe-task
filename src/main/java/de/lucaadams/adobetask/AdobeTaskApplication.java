package de.lucaadams.adobetask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={"de.lucaadams.adobetask"})
public class AdobeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdobeTaskApplication.class, args);
	}
}
