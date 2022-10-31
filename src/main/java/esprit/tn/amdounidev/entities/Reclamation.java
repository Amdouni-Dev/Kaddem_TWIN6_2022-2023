package esprit.tn.amdounidev.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRec")
    private long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateRec")
    private Date date;
    @Column(name = "titreRec")
    private String title;
    @Column(name = "messageRec")
    private String message;
    @Column(name = "imageRec")
    private String image;
    @Column(name = "traiteRec")
    private boolean processed;

    @ManyToOne
    Etudiant etudiant;

}
