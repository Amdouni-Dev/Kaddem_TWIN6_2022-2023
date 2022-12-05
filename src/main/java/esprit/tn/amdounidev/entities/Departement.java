package esprit.tn.amdounidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder // bech nasna3 ay type de constructeur
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDepartement")
    private Long idDepartement;

    @Column(name="nomDepartement")
    private String nomDepartement;

    @Column(name="nombre_classes")
    private int nombre_classes;

    @Column(name="nombre_detage")
    private int nombre_detage;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "departement")
    @JsonIgnore
    private Set<Etudiant> etudiants;
}
