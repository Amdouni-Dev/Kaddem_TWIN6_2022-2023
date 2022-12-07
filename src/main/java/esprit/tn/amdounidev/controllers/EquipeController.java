package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Exceptions.EquipeNotFoundException;
import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;

import esprit.tn.amdounidev.Repository.EtudiantRepository;
import esprit.tn.amdounidev.Repository.ImageEquipeRepo;
import esprit.tn.amdounidev.Services.EquipeServiceImpl;
import esprit.tn.amdounidev.Services.ExcelService;
import esprit.tn.amdounidev.Services.ImageEquipeService;
import esprit.tn.amdounidev.Services.ServiceFile;
import esprit.tn.amdounidev.entities.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/equipe")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Gestion des equipes")

public class EquipeController {

    @Autowired
    EquipeServiceImpl equipeService;
    @Autowired
    EquipeRepository repo;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    DetailEquipeRepository detailEquipeRepository;

    String subPath = "";

    @Autowired
    ServiceFile sf;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien ! "),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Toutes les equipes ",description = "Afficher la liste des equipes seulement ")
    @GetMapping("/All")
    public List<Equipe> findEquipeList() {
        return (List<Equipe>) repo.findAll();
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien ! "),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Toutes les equipes + Details",description = "Afficher la liste des equipes avec la liste des details equipes ")
    @GetMapping("/equipesAndDetails")
    public List<DetailEquipe> equipeAndDetails() {
        return repo.equipesAndDetails();
    }
    // notes = "Equipe  doit s'exister")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien ! "),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide"),
            @ApiResponse(responseCode = "404", description = "!!!! equipe inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Changer la validité d\'une equipe",description = "changer la validité d'une equipe, par defaut une equipe est non validée(false)")
    @PostMapping("/ChangeValiditeEquipe/{id}")
    public Boolean ChangeIsValid(Equipe equipe, Long id) {
        return equipeService.isValid(equipe, id);
    }

    @Operation(summary = "Changer la validité d\'une equipe",description = "changer la validité d'une equipe, par defaut une equipe est non validée(false) \n En plus de changer la date d'activation d'une equipe")
    // @RouterOperation(operation = @Operation(description = "changer la validité d'une equipe, par defaut une equipe est non validée(false) \n En plus de changer la date d'activation d'une equipe"
    //       ,responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Equipe.class)))))
    //@ApiOperation(value = "Gets customer by ID",
    //   response = Equipe.class,
    // notes = "Equipe  doit s'exister")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Changé"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide"),
            @ApiResponse(responseCode = "404", description = "!!!! equipe inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @GetMapping("/changeV/{idEquipe}")
    public void ChangeValid(@PathVariable("idEquipe") Long id) {

     Equipe e=repo.findById(id).get();
     DetailEquipe d=e.getDetailEquipe();
     if(e.getIsValid()==false){
         e.setIsValid(true);
         d.setDateActivation(new Date());
         detailEquipeRepository.save(d);
         repo.save(e);
     }else {
         e.setIsValid(false);

         repo.save(e);
     }

        /*List<Equipe> list = (List<Equipe>) repo.findAll();
        for (Equipe equipe : list) {
            if (equipe.getIdEquipe() == id && equipe.getDetailEquipe().getIdDetailEquipe() == detailEquipe.getIdDetailEquipe()) {
                detailEquipe.setDateActivation(new Date());
                if (equipe.getIsValid() == true) {
                    repo.changeValiditeEquipe(id, false);
                    System.out.println("*****************biennnnnnnnnnn**************************");
                } else {
                    repo.changeValiditeEquipe(id, true);
                    System.out.println("*****************Nooooooooooooo**************************");
                }
            }
        }*/
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Supprimé"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide(detail ou equipe)"),
            @ApiResponse(responseCode = "404", description = "!!!! equipe et/ou detail equipe  inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Changer l\'etat de suppression d\'une equipe",description = "changer l'etat de suppression d'une equipe +changement de la date de suppression d'une equipe")
    @PostMapping("/changeDelete/{idEquipe}/{idDetail}")
    public void ChangeDelete(@PathVariable("idEquipe") Long id, @PathVariable("idDetail") Long idDetail) {
        DetailEquipe detailEquipe = detailEquipeRepository.findById(idDetail).orElseThrow();
        List<Equipe> list = (List<Equipe>) repo.findAll();
        for (Equipe equipe : list) {
            if (equipe.getIdEquipe() == id && equipe.getDetailEquipe().getIdDetailEquipe() == detailEquipe.getIdDetailEquipe()) {
                detailEquipe.setDateSuppression(new Date());
                if (equipe.getIsDeleted() == true) {
                    repo.changeDeleteEquipe(id, false);
                    System.out.println("*****************biennnnnnnnnnn**************************");
                } else {
                    repo.changeDeleteEquipe(id, true);
                    System.out.println("*****************Nooooooooooooo**************************");
                }
            }
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Ajouter une equipe",description = "ajouter une equipe")
    @PostMapping(value = "addEquipe",consumes = {"multipart/form-data", "application/json"})
    public void AddEquipe(@RequestParam String nomEquipe, @RequestParam Niveau niveau, @RequestParam String salle, @RequestParam String thematique,
                          @RequestParam Integer nombreMaxParticipants,
                          @RequestParam(name = "image",required = false) MultipartFile file
                          ) throws IOException {
        String imageName = "equipe.jpg";
        if (file != null) {
            imageName = sf.saveFile(file, this.subPath);

        }
        Equipe e=new Equipe();
        e.setImage(imageName);
        e.setNomEquipe(nomEquipe);
        DetailEquipe detailEquipe=new DetailEquipe();

        detailEquipe.setDateCreation(new Date());
        detailEquipe.setDateActivation(new Date());
        detailEquipe.setDateSuppression(new Date());
        detailEquipe.setSalle(salle);
        detailEquipe.setNombreParticipants(nombreMaxParticipants);
        detailEquipe.setThematique(thematique);

        /***********          Image**/


        //  detailEquipeRepository.save(detailEquipe);
        // equipe.setDetailEquipe(detailEquipe);
        e.setIsValid(false);
        e.setIsDeleted(false);
        e.setDetailEquipe(detailEquipe);

        detailEquipeRepository.save(e.getDetailEquipe());
        e.setNiveau(niveau);

repo.save(e);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(description = "Ajouter une equipe ainsi que ses details")
    @PostMapping("/AddEquipeAndDetail")
    public void AddEquipeAndDetail(@RequestBody Equipe equipe, @RequestBody DetailEquipe detailEquipe) {

        equipeService.saveEquipeAndDetail(equipe, detailEquipe);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien mis a jour"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide"),
            @ApiResponse(responseCode = "404", description = "!!!!  equipe  inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})

    @Operation(summary = "Mettre a jour une equipe",description = "Mettre a jour une equipe via son id")
    @PutMapping("updateEquipe/{id}")
    public void updateEquipe(@RequestBody Equipe equipe, @PathVariable Long id) {

        equipeService.updateEquipe(equipe, id);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien !!"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide(detail ou equipe)"),
            @ApiResponse(responseCode = "404", description = "!!!! equipe  inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})

    @Operation(summary = "Afficher une  equipe",description = "Retourner une equipe via son id")
    @GetMapping("equipe/{id}")
    public Equipe getEquipeById(@PathVariable Long id) {






        return repo.findById(id).orElseThrow(() -> new EquipeNotFoundException(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Supprimé"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide"),
            @ApiResponse(responseCode = "404", description = "!!!!  equipe  inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})

    @Operation(summary = "Supprimer une equipe",description = "Supprimer definitivement une equipe")
    @DeleteMapping("deleteEquipe/{id}")
    public void deleteEquipeById(@PathVariable Long id) {
        equipeService.deleteEquipeById(id);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien !!"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide(detail ou equipe)"),
            @ApiResponse(responseCode = "404", description = "!!!! equipe  inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})

    @Operation(summary = "Afficher detail equipe",description = "Retourner detail equipe via son id equipe")
    @GetMapping("DetailEquipe111/{id}")
    public DetailEquipe getDetailEquipeByIdEquipe(@PathVariable Long id) {

        return repo.findDetailByIdEquipe(id);
    }
/**********************************Membre Equipe*/
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
        @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
        @ApiResponse(responseCode = "500", description = "erreur serveur")})
@Operation(summary = "Ajouter Membre Equipe",description = "Ajouter Membre Equipe")
@PutMapping("/ajouterMembreEquipe/{idEq}/{idEt}")
public void ajouterMembreEquipe(@PathVariable Long idEq,@PathVariable Long idEt) {

    equipeService.ajouterMembreEquipe(idEq,idEt);

}

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Membre To Responsable Equipe",description = "Membre To ResponsableEquipe")
    @PutMapping("/MembreToResponsableEquipe")
    public void MembreToResponsableEquipe(@RequestParam Long idEt,@RequestParam Long idEquipe) {

        equipeService.MembreToResponsableEquipe(idEt, idEquipe);

    }


    /************************ Image Equipe*/
    @Autowired
    ImageEquipeRepo ir;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Ajouter Image Equipe",description = "Ajouter Image Equipe")
    @PostMapping( value = "AjouterImageToEquipe",consumes = "multipart/form-data")
    public ResponseEntity<ImageEquipeResponse> uplaodImage(@RequestParam("image") MultipartFile file,@RequestBody Equipe equipe)
            throws IOException {

        ir.save(ImageEquipe.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageEquipeService.compressImage(file.getBytes())).build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageEquipeResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "affecter Image To Equipe",description = "affecter Image To Equipe")
    @PutMapping("/affecterImageToEquipe")
    public void affecterImageToEquipe(@RequestParam Long idEeq,@RequestParam Long idIm) {

        equipeService.affecterImageToEquipe(idEeq,idIm);

    }
    /***              Pagination       **/
    @GetMapping("/pagination")
    @Operation(summary = "Equipes Paginées",description = "Equipes Paginées")
    public EquipeResponse getAllEqP(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return equipeService.getAllEquipes(pageNo, pageSize, sortBy, sortDir);
    }


    /**
     *
     *                       STATISTIQUE*/
    @Operation(summary = "Nombres de tous les responsables",description = "Nombres de tous les responsables")
    @GetMapping("/nbResponsableEquipes")
    public int nbResponsableEquipes(){
        return repo.nbResponsableEquipes();
    }
    @Operation(summary = "Nombre Membres pour toutes les equipes",description = "Nombre Membres pour toutes les equipes")
    @GetMapping("/nbMembresEquipes")
    public int nbMembresEquipes(){
        return repo.nbMembresEquipes();
    }
    @Operation(summary = "Nombre des membres par equipe",description = "Nombre des membres par equipe")

    @GetMapping("/nbMembresParEquipes/{idEquipe}")
    public int nbMembresParEquipes(@PathVariable Long idEquipe){
        return repo.nbMembresParEquipes(idEquipe);
    }
    @Operation(summary = "Nombre des responsables par equipe",description = "Nombre des responsables par equipe")
    @GetMapping("/nbResponsableParEquipes")
    public int nbResponsableParEquipes(@RequestParam Long idEquipe){
        return repo.nbResponsableParEquipes(idEquipe);
    }
    @Operation(summary = "Nombre d'equipes desactivées",description = "Nombre d'equipes desastivées")
    @GetMapping("/nbEquipesDesactives")
    public int nbEquipesDesactives(){
        return repo.nbEquipesDesactives();
    }
    @Operation(summary = "Nombre d'equipes activées",description = "Nombre d'equipes activées")
    @GetMapping("/nbEquipesActives")
    public int nbEquipesActives(){
        return repo.nbEquipesActives();
    }
    @Operation(summary = "Nombre d'equipes ",description = "Nombre de toutes les equipes")
    @GetMapping("/nbEquipes")
    public int nbEquipes(){
        return repo.nbEquipes();
    }
    @Operation(summary = "supprimerEtudiantFromEquipe",description = "supprimerEtudiantFromEquipe")
    @GetMapping("/supprimerEtudiantFromEquipe/{idEt}/{idEq}")
    public void supprimerEtudiantFromEquipe(@PathVariable Long idEt,@PathVariable Long idEq){
         equipeService.supprimerEtudiantFromEquipe(idEt,idEq);

    }
    @Operation(summary = "nbEquipeCetteHeure",description = "nbEquipeCetteHeure")
    @GetMapping("/nbEquipeCetteHeure")
    public DetailEquipe nbEquipeCetteHeure(){

        Date d=new Date();
        String d2=d.toString();
String d3=d2.substring(11,13);

        System.out.println("***************Date  "+d2.substring(24,28));

     // return null;
    return  detailEquipeRepository.nbEquipeCetteHeure();

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Ajouter une equipe",description = "ajouter une equipe")
    @PostMapping( "/addEquipe2")
    @RequestMapping(value = "addEquipe2", method = RequestMethod.POST)
    //@RequestMapping(value = "/checkData/{username}/{password}", method = RequestMethod.POST)
    //public String Authentication(@PathParam("username") String username, @PathParam("password") String password) {
    //
    //}
    public void AddEquipe2(@RequestParam("nomEquipe") String nomEquipe) throws IOException {
        String imageName = "equipe.jpg";

        Equipe e=new Equipe();
        e.setImage(imageName);
        e.setNomEquipe(nomEquipe);


        /***********          Image**/


        //  detailEquipeRepository.save(detailEquipe);
        // equipe.setDetailEquipe(detailEquipe);
        e.setIsValid(false);
        e.setIsDeleted(false);




        repo.save(e);

    }

    @PostMapping(consumes = {"multipart/form-data", "application/json"})
    public void create( @RequestParam("nomEquipe") String nomEquipe,@RequestParam(name = "image",required = false) MultipartFile file

            ,@RequestParam ("salle") String salle ,@RequestParam("thematique") String thematique,@RequestParam("nombreMaxParticipants") int nombreMaxParticipants,@RequestParam("idEtudiant") Long idEtudiant) throws IOException {


        String imageName = "equipe.jpg";

        if (file != null) {
            System.out.println("**************1111111111111111***************");
            imageName = sf.saveFile(file, this.subPath);
        }

Equipe e=new Equipe();
DetailEquipe d=new DetailEquipe();
Etudiant etudiant=etudiantRepository.findById(idEtudiant).get();
/**
 *  DetailEquipe detailEquipe=equipe.getDetailEquipe();
 *
 *        detailEquipe.setDateCreation(new Date());
 *        detailEquipe.setDateActivation(new Date());
 *         detailEquipe.setDateSuppression(new Date());
 *
 *
 *
 *        //  detailEquipeRepository.save(detailEquipe);
 *        // equipe.setDetailEquipe(detailEquipe);
 *equipe.setIsValid(false);
 *equipe.setIsDeleted(false);
 *detailEquipeRepository.save(equipe.getDetailEquipe());*/
        d.setDateCreation(new Date());
        d.setDateActivation(new Date());
        d.setDateSuppression(new Date());
        d.setSalle(salle);
        d.setThematique(thematique);
        d.setNombreParticipants(nombreMaxParticipants);
        detailEquipeRepository.save(d);
e.setEtudiant(etudiant);

        e.setNomEquipe(nomEquipe);
        e.setIsValid(false);
        e.setIsDeleted(false);
e.setNiveau(Niveau.JUNIOR);
        e.setImage(imageName);
e.setDetailEquipe(d);

        repo.save(e);
        equipeService.MembreToResponsableEquipe(idEtudiant,e.getIdEquipe());
        etudiantRepository.save(etudiant);
    }





    @Autowired
    private JavaMailSender sender;

    @PostMapping("/SendMessageToAdminOfEquipes")
    public void sendMail(@RequestParam("title") String title,@RequestParam("body") String body) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());





        try {
            helper.setTo("mounaamdouni15@gmail.com");
          //  helper.setFrom("mounaamdouni15@gmail.com");
            helper.setSubject(title);
            helper.setText(body,true);

        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);



    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "afficher la liste des membre d'equipe",description = "afficher la liste des membre par Id Equipe")
    @GetMapping("/getMembers/{id}")
    public List<Etudiant> getMembers( @PathVariable Long id) {
      Equipe e=repo.findById(id).get();
      List<Etudiant> etudiants=e.getEtudiants();
      return etudiants;
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "delete Etudiant From Equipe",description = "La suppression est partielle (supprimé mais pas definitivement)" +
            "l'etudiant danjs ce cas ne peut reagir dans l'equipe")
    @PostMapping("/deleteEtudiantFromEquipe/{id}")
    public Etudiant deleteEtudiantFromEquipe( @PathVariable Long id) {
      return equipeService.deleteEtudiantFromEquipe(id);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "delete Etudiant From Equipe",description = "cette methode permet de rajouter l'etudiant apres son supression temporaire de l'equipe")
    @PostMapping("/ValiderEtudiant/{id}")
    public Etudiant ValiderEtudiant( @PathVariable Long id) {
        return equipeService.ValiderEtudiant(id);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "getEtudiantsNonResponsables",description = "cette methode permet de retourner les etudiants qui ne sont pas responsables des equipes")
    @GetMapping("/getEtudiantsNonResponsables")
    public List<Etudiant> getEtudiantsNonResponsables( ) {
        return repo.getEtudiantsNonResponsables();

    }

    //@Autowired
//    EquipeServiceImpl equipeService;
//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail() throws MessagingException {
//        equipeService.sendSimpleEmail("mounaamdouni213@gmail.com",
//                "This is email body",
//                "This is email subject");
//
//    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Pagination des equipes",description = "cette methode permet de retourner la  Pagination des equipes")

    @GetMapping("/getEquipeList")
    public Page<Equipe> getEquipeList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        return equipeService.findAll(paging);
    }

@Autowired
    ExcelService s;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "telecharger Equipes",description = "cette methode permet de telecharger toutes les equipes sous format Excel")
    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "equipes.xlsx";
        InputStreamResource file = new InputStreamResource(s.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
