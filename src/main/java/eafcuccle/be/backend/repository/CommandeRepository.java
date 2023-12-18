package eafcuccle.be.backend.repository;

import eafcuccle.be.backend.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}