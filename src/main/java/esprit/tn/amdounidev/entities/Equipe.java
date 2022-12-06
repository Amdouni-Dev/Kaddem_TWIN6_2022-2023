package esprit.tn.amdounidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder // bech nasna3 ay type de constructeur
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEquipe")
    private Long idEquipe;
    @Column(name="nomEquipe")
    private String nomEquipe;
    @Enumerated(EnumType.ORDINAL)
    Niveau niveau;
    @Column(name="isDeleted")
    private Boolean isDeleted;
    @Column(name = "isValid")
    private Boolean isValid;
    @Column(name = "image")
    private String image;
  //  @Column(name="nombreMaxParticipant")
    //private int nombreMaxParticipant;


    @OneToOne(cascade = CascadeType.ALL)
    private DetailEquipe detailEquipe;

    @ManyToMany
    @JsonIgnore
    private List<Etudiant> etudiants;
   @OneToOne
    ImageEquipe imageEquipe;
   @OneToOne
   Etudiant etudiant;



}
