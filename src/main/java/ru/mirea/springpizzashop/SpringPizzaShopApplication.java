package ru.mirea.springpizzashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringPizzaShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPizzaShopApplication.class, args);
    }

}
