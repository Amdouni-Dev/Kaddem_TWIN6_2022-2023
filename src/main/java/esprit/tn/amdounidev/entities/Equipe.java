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
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEquipe")
    private Long idEquipe;
    @Column(name="nomEquipe")
    private String nomEquipe;
    @Enumerated(EnumType.ORDINAL)
    Niveau niveau;

    @OneToOne
    private DetailEquipe detailEquipe;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Etudiant> etudiants;


}
