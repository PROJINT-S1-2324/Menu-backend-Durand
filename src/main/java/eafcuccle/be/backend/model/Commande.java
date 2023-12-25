package eafcuccle.be.backend.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "plat_id", nullable = false)
   // @JsonBackReference
    private Plat plat;

    private int quantite;
    /*private String nomPlat;
    private double prixPlat;
    public String getNomPlat() {
        return nomPlat;
   }
    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public double getPrixPlat() {
        return prixPlat;
    }

    public void setPrixPlat(double prixPlat) {
        this.prixPlat = prixPlat;
    }
*/
    public Commande() {
    }
    public Commande( Plat plat, int quantite) {
        this.plat = plat;
        this.quantite = quantite;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}