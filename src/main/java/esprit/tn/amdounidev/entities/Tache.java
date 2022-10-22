package esprit.tn.amdounidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Tache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTache")
    private Long idTache;
    @Column(name="descriptionTache")
    private String descriptionTache;
    @Enumerated(EnumType.ORDINAL)
    Etat etatTache;

    @ManyToOne
    @JoinColumn( name="idProjet" )
    @JsonIgnore
    private Projet projet;

}
