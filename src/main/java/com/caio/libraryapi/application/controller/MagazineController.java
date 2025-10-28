package com.caio.libraryapi.application.controller;

import com.caio.libraryapi.application.request.MagazineRequest;
import com.caio.libraryapi.domain.model.Magazine;
import com.caio.libraryapi.domain.service.MagazineService;
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
    public Magazine create(@RequestBody MagazineRequest request) {
        var magazine = new Magazine();
        magazine.setTitle(request.getTitle());
        magazine.setAuthor(request.getAuthor());
        magazine.setPublicationYear(request.getPublicationYear());
        magazine.setIssn(request.getIssn());
        magazine.setVolume(request.getVolume());
        magazine.setNumber(request.getNumber());
        return magazineService.save(magazine);
    }

    @GetMapping("/{id}")
    public Magazine findById(@PathVariable Long id) {
        return magazineService.findById(id);
    }

    @GetMapping
    public Collection<Magazine> findAll() {
        return magazineService.findAll();
    }

    @PutMapping("/{id}")
    public Magazine update(@PathVariable Long id, @RequestBody MagazineRequest request) {
        var magazine = new Magazine();
        magazine.setId(id);
        magazine.setTitle(request.getTitle());
        magazine.setAuthor(request.getAuthor());
        magazine.setPublicationYear(request.getPublicationYear());
        magazine.setIssn(request.getIssn());
        magazine.setVolume(request.getVolume());
        magazine.setNumber(request.getNumber());
        return magazineService.save(magazine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        magazineService.deleteById(id);
    }

}
