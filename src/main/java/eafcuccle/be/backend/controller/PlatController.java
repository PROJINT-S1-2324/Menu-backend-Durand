package eafcuccle.be.backend.controller;

import eafcuccle.be.backend.model.Plat;
import eafcuccle.be.backend.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

public class PlatController {

    @Autowired
    private PlatRepository platRepository;

    @GetMapping("/plat")
    public ResponseEntity<List<Plat>> getPlats() {
        List<Plat> plat = platRepository.findAll();
        return new ResponseEntity<>(plat, HttpStatus.OK);
    }

    @PostMapping("/plat")
    public ResponseEntity<Plat> ajouterPlat(@RequestBody Plat plat) {
        Plat nouveauPlat = platRepository.save(plat);
        return new ResponseEntity<>(nouveauPlat, HttpStatus.CREATED);
    }
}


