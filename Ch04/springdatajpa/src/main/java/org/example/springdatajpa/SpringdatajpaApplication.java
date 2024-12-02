package org.example.springdatajpa;

import org.example.springdatajpa.model.User;
import org.example.springdatajpa.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class SpringdatajpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaApplication.class, args);
    }

    @Bean
    public ApplicationRunner configure(UserRepository userRepository) {
        return env -> {
            User user1 = new User("Thinh", LocalDate.of(2024, Month.DECEMBER,1));
            User user2 = new User("Hoang", LocalDate.of(2024, Month.NOVEMBER,2));

            userRepository.save(user1);
            userRepository.save(user2);

            userRepository.findAll().forEach(System.out::println);

        };
    }
}
