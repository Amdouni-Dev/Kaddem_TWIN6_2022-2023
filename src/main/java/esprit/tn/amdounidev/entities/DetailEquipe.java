package esprit.tn.amdounidev.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder // bech nasna3 ay type de constructeur
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DetailEquipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDetailEquipe")
    private long idDetailEquipe;

    @Column(name="salle")
    private int salle;
    @Column(name = "thematique")
    private String thematique;
    @OneToOne(mappedBy = "detailEquipe")
    private Equipe equipe;
}
