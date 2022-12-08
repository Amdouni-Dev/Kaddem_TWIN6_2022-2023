package esprit.tn.amdounidev.entities;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Map;
@Data
public class Notee {
    private String subject;
    private String content;
    private Map<String, String> data;
}
