package infectiontracer.rest;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/** Class that starts the SpringBoot server that the REST-API uses. */
@SpringBootApplication
public class InfectionTracerApplication {

  public static void main(String[] args) {
    SpringApplication.run(InfectionTracerApplication.class, args);
  }

  /**
   * Method that prints server information.
   *
   * @param ctx The application context
   * @return A CommandLineRunner
   */
  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      System.out.println("Let's inspect the beans provided by Spring Boot:");

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        System.out.println(beanName);
      }
    };
  }
}
