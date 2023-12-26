package eafcuccle.be.backend.controller;
import eafcuccle.be.backend.Authorization.Permision;
import eafcuccle.be.backend.model.Commande;
import eafcuccle.be.backend.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import eafcuccle.be.backend.repository.PlatRepository;
import eafcuccle.be.backend.Authorization.User;
import eafcuccle.be.backend.Authorization.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController

public class CommandeController {
    private static final Logger logger = LoggerFactory.getLogger(CommandeController.class);
    @Autowired
    private PlatRepository platRepository;

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private UserRepository userRepository;
/*
@GetMapping("/commande")
    public ResponseEntity<List<Commande>> getAllCommandes(Authentication authentication) {
    String userId=authentication.getName();
    User user = getUserOrCreate(userId);
    if (user.isAllowedTo(Permision.READ_MENU)) {
        List<Commande> commandes = commandeRepository.findAll();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    } else {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
*/
@GetMapping("/commande")
public ResponseEntity<List<Commande>> getAllCommandes(
        Authentication authentication,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int pageSize
) {
    String userId = authentication.getName();
    User user = getUserOrCreate(userId);

    if (user.isAllowedTo(Permision.READ_MENU)) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<Commande> commandesPage = commandeRepository.findAll(pageable);

        List<Commande> commandes = commandesPage.getContent();
        logger.info("Liste des commandes récupérée avec succès. Nombre de commandes : {}", commandes.size());
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    } else {
        logger.warn("L'utilisateur n'est pas autorisé à accéder à la liste des commandes.");
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



    @PostMapping("/commande")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande) {

        // Si les données sont valides, ajoutez la commande à la base de données
        Commande nouveauCommande = commandeRepository.save(commande);
        logger.info("Nouvelle commande ajoutée avec succès. ID de commande : {}", nouveauCommande.getId());
        return ResponseEntity.ok(nouveauCommande);
    }
    // Endpoint pour récupérer toutes les commandes
    /*@GetMapping("/commande")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }
*/

    @GetMapping("/{commandeId}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long commandeId) {
        return commandeRepository.findById(commandeId)
                .map(commande -> new ResponseEntity<>(commande, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/commandeDelete/{commandeId}")
    public ResponseEntity<Void> supprimerCommande(@PathVariable Long commandeId, Authentication authentication) {
        String userId = authentication.getName();
        User user = getUserOrCreate(userId);
        if (user.isAllowedTo(Permision.DELETE_COMMAND)) {
            if (commandeRepository.existsById(commandeId)) {
                commandeRepository.deleteById(commandeId);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}