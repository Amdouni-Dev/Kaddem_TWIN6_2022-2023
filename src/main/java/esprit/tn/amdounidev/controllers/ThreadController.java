package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Repository.ThreadRepository;
import esprit.tn.amdounidev.Services.ThreadServiceImpl;
import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Thread;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Thread")
public class ThreadController {

    @Autowired
    ThreadRepository rr;
    @Autowired
    ThreadServiceImpl ThreadService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Thread list "),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Threads ",description = "Thread list")
    @GetMapping("/")
    public ResponseEntity<List<Thread>> findThreadList(){
        return new ResponseEntity <List<Thread>>(rr.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Thread> findThreadById(@PathVariable Long id) {

        return new ResponseEntity <>(ThreadService.findThreadById(id), HttpStatus.FOUND);
    }

    @PostMapping("/AddThread")
    public ResponseEntity<Thread> AddThread(@RequestBody Thread Thread){

        return new ResponseEntity<>(ThreadService.addThread(Thread), HttpStatus.CREATED);

    }

    @PostMapping("/updateThread")
    public Thread updateThread(@RequestBody Thread Thread){
        return ThreadService.updateThread(Thread);
    }
    @GetMapping("/deleteThread/{id}")
    public ResponseEntity<?> deleteDepartment( @PathVariable Long id) {
        ThreadService.deleteThread(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
