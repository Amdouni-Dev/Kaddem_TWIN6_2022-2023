package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Repository.ThreadTypeRepository;
import esprit.tn.amdounidev.Services.ThreadTypeServiceImpl;
import esprit.tn.amdounidev.entities.ThreadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ThreadType")
public class ThreadTypeController {

    @Autowired
    ThreadTypeRepository rr;
    @Autowired
    ThreadTypeServiceImpl ThreadTypeService;

    @GetMapping("/")
    public List<ThreadType> findThreadTypeList(){
        return (List<ThreadType>) rr.findAll();
    }

    @GetMapping("/{id}")
    public ThreadType findThreadTypeById( @PathVariable Long id) {
        return ThreadTypeService.findThreadTypeById(id);
    }

    @PostMapping("/AddThreadType")
    public ThreadType AddThreadType(@RequestBody ThreadType ThreadType){

        ThreadTypeService.addThreadType(ThreadType);
        return ThreadType;
    }

    @PostMapping("/updateThreadType")
    public ThreadType updateThreadType(@RequestBody ThreadType ThreadType){
        return ThreadTypeService.updateThreadType(ThreadType);
    }
    @GetMapping("/deleteThreadType/{id}")
    public void deleteDepartment( @PathVariable Long id) {
        ThreadTypeService.deleteThreadType(id);
    }
}
