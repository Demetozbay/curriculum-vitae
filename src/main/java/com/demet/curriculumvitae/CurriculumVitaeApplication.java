package com.demet.curriculumvitae;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class CurriculumVitaeApplication {
    public static final int DEFAULT_CONTENT_SIZE = 20;
    public static final Sort DEFAULT_SORT = Sort.by("creationDate")
                                                .descending();

    @Bean
    public CommandLineRunner temp() {
        return args -> {
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CurriculumVitaeApplication.class, args);
    }
}
