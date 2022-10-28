package esprit.tn.amdounidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Thread implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "question")
    private String salle;

    @Column(name = "nb_likes")
    private int nb_likes;

    @Column(name = "postDate")
    private LocalDateTime postDate;

    @Column(name = "display")
    private boolean display;

    @Column(name = "verified")
    private String verified;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "thread")
    private Set<Reponse> reponses;

    @JsonIgnore
    @ManyToOne
    private ThreadType threadType;

    @JsonIgnore
    @ManyToOne
    private Etudiant etudiant;

}
