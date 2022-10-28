package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ThreadRepository;
import esprit.tn.amdounidev.entities.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadServiceImpl implements ThreadService{
    @Autowired
    ThreadRepository tr;
    @Override
    public Thread addThread(Thread t) {
        return  tr.save(t);
    }

    @Override
    public List<Thread> addThread(List<Thread> listThread) {
        return tr.saveAll(listThread);
    }

    @Override
    public Thread updateThread(Thread t) {
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
}
