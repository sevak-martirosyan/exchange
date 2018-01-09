package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.JpyService;


@SpringBootApplication
@ComponentScan(basePackages = {
        "web", "service", "repository", "com"},
        basePackageClasses = Application.class)
@EntityScan("model")
@EnableJpaRepositories("repository")
public class Application {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.getBean(JpyService.class).fillRepositoryEveryHour();

    }
}
