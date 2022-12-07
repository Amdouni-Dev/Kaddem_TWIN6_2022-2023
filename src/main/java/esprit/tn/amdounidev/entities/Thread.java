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
public class Thread implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "question")
    private String question;

    @Column(name = "object")
    private String object;

    @Column(name = "nb_likes")
    private int nb_likes;

    @Column(name = "postDate")
    private LocalDateTime postDate;

    @Column(name = "display")
    private boolean display;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;




    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "thread")
    private Set<Reponse> reponses;


    @ManyToOne
    @JsonIgnore
    private Etudiant etudiant;

}
