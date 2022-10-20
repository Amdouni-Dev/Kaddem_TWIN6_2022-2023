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
public class Equipe {
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



    @JsonIgnore
    @OneToOne
    private DetailEquipe detailEquipe;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Etudiant> etudiants;


}
