package esprit.tn.amdounidev.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Date;

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
    private String salle;
    @Column(name = "thematique")
    private String thematique;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateActivation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSuppression;
    @Column(name="nombreParticipants")
    private Integer nombreParticipants;
    @Column(name="nombreImages")
    private Integer nombreImages;





}
