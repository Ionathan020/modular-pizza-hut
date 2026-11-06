package dev.jonathanstronkhorst.modularpizzahut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ModularPizzaHutApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModularPizzaHutApplication.class, args);
    }

}
