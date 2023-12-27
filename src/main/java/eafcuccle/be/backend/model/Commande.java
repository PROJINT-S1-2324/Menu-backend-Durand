package eafcuccle.be.backend.model;
import jakarta.persistence.*;


@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "plat_id", nullable = false)

    private Plat plat;

    private int quantite;

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