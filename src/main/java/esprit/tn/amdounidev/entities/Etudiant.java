package esprit.tn.amdounidev.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder // bech nasna3 ay type de constructeur
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idE")
    private Long  idE;
    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Enumerated(EnumType.ORDINAL)
    Domaine domaine;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etudiant")
    private Set<Contrat> contrats;
    @ManyToOne
    private Departement departement;
    @ManyToMany(mappedBy ="etudiants" )
    private List<Equipe> equipes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etudiant")
    private Set<Thread> threads;


    // Mouna **************
    @Enumerated(EnumType.STRING)
    private RoleEtudiantEquipe roleEtudiantEquipe;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEntreeEquipe;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortieEquipe;
    @Column(name="isDeletedFromEquipe")
    private Boolean isDeletedFromEquipe;
    @OneToOne(mappedBy = "etudiant")
    @JsonIgnore
    Equipe equipe;

}
