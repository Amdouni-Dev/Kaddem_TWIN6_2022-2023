package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ThreadRepository;
import esprit.tn.amdounidev.entities.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ThreadServiceImpl implements ThreadService{
    @Autowired
    ThreadRepository tr;
    @Override
    public Thread addThread(Thread t) {
        t.setPostDate(LocalDateTime.now());
        t.setUpdatedAt(LocalDateTime.now());

        return  tr.save(t);
    }

    @Override
    public List<Thread> addThread(List<Thread> listThread) {
        return tr.saveAll(listThread);
    }

    @Override
    public Thread updateThread(Thread t) {

        t.setUpdatedAt(LocalDateTime.now());
        return tr.save(t);
    }

    @Override
    public List<Thread> updateThread(List<Thread> listThread) {
        return tr.saveAll(listThread);
    }

    @Override
    public void deleteThread(Long id) {
        tr.deleteById(id);
    }

    @Override
    public void deleteThread(Thread t) {
        tr.delete(t);
    }

    @Override
    public List<Thread> findAllThread() {
        return tr.findAll();
    }

    @Override
    public Thread findThreadById(Long id) {
        return tr.findById(id).orElse(new Thread());
    }



    //to do : it takes student id and returns the full name
    public String etudiantByName(Long idE){return "something";}

    // to do : takes id thread and ide and check if this kid liked the post already check the first row of the thread table we made it for likes and all
    public boolean likedThread (Long idT,Long idE){
        return false;
    }

    //bad words detector check and compare the 2nd Thread row


}
