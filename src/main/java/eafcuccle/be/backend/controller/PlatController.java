package eafcuccle.be.backend.controller;

import eafcuccle.be.backend.Authorization.Permision;
import eafcuccle.be.backend.Authorization.Role;
import eafcuccle.be.backend.Authorization.User;
import eafcuccle.be.backend.Authorization.UserRepository;
import eafcuccle.be.backend.model.Plat;
import eafcuccle.be.backend.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController

public class PlatController {
    private static final Logger logger = LoggerFactory.getLogger(PlatController.class);

    @Autowired
    private PlatRepository platRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/plat")
    public ResponseEntity<Plat> ajouterPlat(@RequestBody Plat plat, Authentication authentication) {

        String userId=authentication.getName();
        User user = getUserOrCreate(userId);
        if (user.isAllowedTo(Permision.WRITE_MEU)) {
            Plat nouveauPlat = platRepository.save(plat);
            logger.info("Nouveau plat ajouté avec succès. ID du plat : {}", nouveauPlat.getId());
            return  ResponseEntity.ok(nouveauPlat);
        } else {
            logger.warn("L'utilisateur n'est pas autorisé à ajouter un nouveau plat.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    private User getUserOrCreate(String userId) {
        Optional<User> user= userRepository.findByUserId(userId);
        if(user.isPresent()){
            return user.get();
        }
        else {
            User newUser= new User(userId);
            return userRepository.save(newUser);
        }
    }

    @GetMapping("/plat")
    public ResponseEntity<List<Plat>> getPlats() {
        List<Plat> plat = platRepository.findAll();
        logger.info("Liste des plats récupérée avec succès. Nombre de plats : {}", plat.size());
        return new ResponseEntity<>(plat, HttpStatus.OK);
    }

    /*
    @PostMapping("/plat")
    public ResponseEntity<Plat> ajouterPlat(@RequestBody Plat plat) {
        Plat nouveauPlat = platRepository.save(plat);
        return new ResponseEntity<>(nouveauPlat, HttpStatus.CREATED);
    }

     */
}


