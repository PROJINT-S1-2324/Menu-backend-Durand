package eafcuccle.be.backend.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
@Entity
@Table(name = "plat")
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private double prix;

    //@OneToMany(mappedBy = "plat", cascade = CascadeType.ALL)
  //  @JsonBackReference
//    private List<Commande> commandes;

    public Plat() {
    }

    public Plat(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    //public List<Commande> getCommandes() {
      //  return commandes;
   // }

    //public void setCommandes(List<Commande> commandes) {
      //  this.commandes = commandes;
    //}
}