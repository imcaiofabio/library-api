package com.caio.libraryapi.application.loader;

import com.caio.libraryapi.domain.model.Magazine;
import com.caio.libraryapi.domain.service.MagazineService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class MagazineLoader implements ApplicationRunner {

    private final MagazineService magazineService;

    public MagazineLoader(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var inputStream = getClass().getClassLoader().getResourceAsStream("data/magazines.csv");
        if (inputStream == null) {
            System.out.println("Magazine data file not found.");
            return;
        }

        try (var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            reader.lines().forEach(line -> {
                var fields = line.split(",");
                if (fields.length == 5) {
                    var magazine = new Magazine();
                    magazine.setTitle(fields[0]);
                    magazine.setAuthor(fields[1]);
                    magazine.setPublicationYear(Integer.parseInt(fields[2]));
                    magazine.setIssn(fields[3]);
                    magazine.setVolume(Integer.parseInt(fields[4]));
                    magazine.setNumber(1); // Defaulting number to 1 as it's missing in the file
                    magazineService.save(magazine);
                }
            });
        }

        System.out.println("Magazines loaded from file:");
        magazineService.findAll().forEach(System.out::println);
    }
}
