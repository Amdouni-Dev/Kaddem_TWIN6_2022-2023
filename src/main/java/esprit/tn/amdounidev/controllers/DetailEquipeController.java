package esprit.tn.amdounidev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DetailEquipe")
public class DetailEquipeController {
    @GetMapping
    public String test(){
        return"hello";
            }

}
