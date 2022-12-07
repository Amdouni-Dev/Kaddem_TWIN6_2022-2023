package esprit.tn.amdounidev.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Log implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idLog;
    @Temporal(TemporalType.DATE)
    private Date dateLog;
    private Integer nouveauMontant;
    private String etudiant;
}
