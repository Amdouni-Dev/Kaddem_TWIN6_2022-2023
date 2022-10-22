package esprit.tn.amdounidev.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTache")
    private Long idTache;
    @Column(name="DescriptionTache")
    private String DescriptionTache;
    @Enumerated(EnumType.ORDINAL)
    Etat etatTache;

    @ManyToOne
    private Projet projet;

}
