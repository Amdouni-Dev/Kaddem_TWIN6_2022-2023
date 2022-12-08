package esprit.tn.amdounidev.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(name = "dateRec")
    private LocalDate date= LocalDate.now();
    @Column(name = "titreRec")
    private String title;
    @Column(name = "messageRec")
    private String message;
    @Column(name = "imageRec")
    private String image;
    @Column(name = "etat", columnDefinition = "boolean default false")
    private Boolean etat=false;

    @ManyToOne
    Etudiant etudiant;

}

