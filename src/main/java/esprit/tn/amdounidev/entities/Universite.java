package esprit.tn.amdounidev.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder // bech nasna3 ay type de constructeur
@AllArgsConstructor
@NoArgsConstructor
public class Universite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUniversite")
    private Long idUniversite;
    @Column(name="nomUniversite")
    private String nomUniversite;
    @Column(name="etatUniversite")
    private String etatUniversite;
    @Column(name="surfaceUniversite")
    private int surfaceUniversite;
    @Column(name="reputationUniversite")
    private String reputationUniversite;

    @Column
    private LocalDateTime date_creation;

    @Column
    private LocalDateTime date_update;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Departement> departments;

}
