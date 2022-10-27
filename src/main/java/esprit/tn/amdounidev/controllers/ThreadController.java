package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Repository.ThreadRepository;
import esprit.tn.amdounidev.Services.ThreadServiceImpl;
import esprit.tn.amdounidev.entities.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Thread")
public class ThreadController {

    @Autowired
    ThreadRepository rr;
    @Autowired
    ThreadServiceImpl ThreadService;

    @GetMapping("/")
    public List<Thread> findThreadList(){
        return rr.findAll();
    }

    @GetMapping("/{id}")
    public Thread findThreadById( @PathVariable Long id) {
        return ThreadService.findThreadById(id);
    }

    @PostMapping("/AddThread")
    public Thread AddThread(@RequestBody Thread Thread){

        ThreadService.addThread(Thread);
        return Thread;
    }

    @PostMapping("/updateThread")
    public Thread updateThread(@RequestBody Thread Thread){
        return ThreadService.updateThread(Thread);
    }
    @GetMapping("/deleteThread/{id}")
    public void deleteDepartment( @PathVariable Long id) {
        ThreadService.deleteThread(id);
    }
}
