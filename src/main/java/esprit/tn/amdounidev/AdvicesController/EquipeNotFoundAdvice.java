package esprit.tn.amdounidev.AdvicesController;
import esprit.tn.amdounidev.Exceptions.EquipeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EquipeNotFoundAdvice {
    @ResponseBody //le corps de la réponse.
    @ExceptionHandler(EquipeNotFoundException.class) // taamel onfiguration  lel conseil pour qu'il ne réponde que si un EquipeNotFoundExceptionest lancé
    @ResponseStatus(HttpStatus.NOT_FOUND) //404
    String EquipeNotFoundHandler(EquipeNotFoundException ex) {
        return ex.getMessage();
    }
}
