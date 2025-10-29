package com.caio.libraryapi.application.controller;

import com.caio.libraryapi.application.request.MagazineRequest;
import com.caio.libraryapi.domain.model.Magazine;
import com.caio.libraryapi.domain.service.MagazineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/magazines")
public class MagazineController {

    private final MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @PostMapping
    public ResponseEntity<Magazine> create(@RequestBody MagazineRequest request) {
        var magazine = new Magazine();
        magazine.setTitle(request.getTitle());
        magazine.setAuthor(request.getAuthor());
        magazine.setPublicationYear(request.getPublicationYear());
        magazine.setIssn(request.getIssn());
        magazine.setVolume(request.getVolume());
        magazine.setNumber(request.getNumber());
        var savedMagazine = magazineService.save(magazine);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMagazine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Magazine> findById(@PathVariable Long id) {
        var magazine = magazineService.findById(id);
        return magazine != null ? ResponseEntity.ok(magazine) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Magazine>> findAll() {
        return ResponseEntity.ok(magazineService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Magazine> update(@PathVariable Long id, @RequestBody MagazineRequest request) {
        var magazine = new Magazine();
        magazine.setId(id);
        magazine.setTitle(request.getTitle());
        magazine.setAuthor(request.getAuthor());
        magazine.setPublicationYear(request.getPublicationYear());
        magazine.setIssn(request.getIssn());
        magazine.setVolume(request.getVolume());
        magazine.setNumber(request.getNumber());
        var updatedMagazine = magazineService.save(magazine);
        return ResponseEntity.ok(updatedMagazine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        magazineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
