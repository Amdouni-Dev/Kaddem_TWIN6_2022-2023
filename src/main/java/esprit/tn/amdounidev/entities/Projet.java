package esprit.tn.amdounidev.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProjet")
    private Long idProjet;
    @Column(name="nomProjet")
    private String nomProjet;
    @Column(name="dureeProjet")
    private String dureeProjet;
    @Enumerated(EnumType.STRING)
    Type typeProjet;
    @Temporal (TemporalType.DATE)
    @Column(name="dateDebutP")
    private Date dateDebutP;
    @Temporal (TemporalType.DATE)
    @Column(name="dateFinP")
    private Date dateFinP;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet",fetch = FetchType.EAGER)
  // @OneToMany(mappedBy = "projet",fetch = FetchType.EAGER)
    private List<Tache> taches;

}
