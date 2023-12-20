package eafcuccle.be.backend.Authorization;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ElementCollection
    @Column(name = "permision")
    @CollectionTable(name = "role_permision", joinColumns = @JoinColumn(name = "role_id"))
    private Set<Permision> permisions = new LinkedHashSet<>();

    public Set<Permision> getPermisions() {
        return permisions;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isAllowedTo(Permision permision) {
        return permisions.contains(permision);
    }
}