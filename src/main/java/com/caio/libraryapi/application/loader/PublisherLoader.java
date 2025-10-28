package com.caio.libraryapi.application.loader;

import com.caio.libraryapi.domain.model.Publisher;
import com.caio.libraryapi.domain.service.PublisherService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@Order(1)
public class PublisherLoader implements ApplicationRunner {

    private final PublisherService publisherService;

    public PublisherLoader(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var inputStream = getClass().getClassLoader().getResourceAsStream("data/publishers.csv");
        if (inputStream == null) {
            System.out.println("Publisher data file not found.");
            return;
        }

        try (var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            reader.lines().forEach(line -> {
                var fields = line.split(",");
                if (fields.length == 3) {
                    var publisher = new Publisher(Long.parseLong(fields[0]), fields[1], fields[2]);
                    publisherService.save(publisher);
                }
            });
        }

        System.out.println("Publishers loaded from file:");
        publisherService.findAll().forEach(System.out::println);
    }
}
