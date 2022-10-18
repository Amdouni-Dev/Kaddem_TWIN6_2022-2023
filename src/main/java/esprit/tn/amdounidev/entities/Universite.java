package esprit.tn.amdounidev.entities;

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
public class Universite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUniversite")
    private Long idUniversite;
    @Column(name="nomUniversite")
    private String nomUniversite;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Departement> departments;
}
