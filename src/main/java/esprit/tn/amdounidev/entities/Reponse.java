package esprit.tn.amdounidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Reponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "reply")
    private String reply;

    @Column(name = "replydate")
    private LocalDateTime replydate;

    @Column(name = "display")
    private boolean display;
    @ManyToOne
    private Thread thread;

    @JsonIgnore
    @ManyToOne
    private Etudiant etudiant;
}
