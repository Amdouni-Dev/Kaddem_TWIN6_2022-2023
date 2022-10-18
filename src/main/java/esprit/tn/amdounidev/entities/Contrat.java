package esprit.tn.amdounidev.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder // bech nasna3 ay type de constructeur
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idContrat")
    private Long idContrat;
    @Temporal (TemporalType.DATE)
    @Column(name="dateDebutC")
    private Date dateDebutC;
    @Temporal(TemporalType.DATE)
    @Column(name ="dateFinC")
    private Date dateFinC;
    @Column(name="archive")
    private Boolean archive;
    @Column(name="montantC")
    private Float montantC;
    @Enumerated(EnumType.ORDINAL)
    Specailite specailite;
    @ManyToOne
    private Etudiant etudiant;


}
