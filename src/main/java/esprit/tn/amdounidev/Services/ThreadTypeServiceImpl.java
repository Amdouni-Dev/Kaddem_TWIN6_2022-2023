package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ThreadTypeRepository;
import esprit.tn.amdounidev.entities.ThreadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadTypeServiceImpl implements ThreadTypeService{

    @Autowired
    ThreadTypeRepository tr;
    @Override
    public ThreadType addThreadType(ThreadType t) {
        return  tr.save(t);
    }

    @Override
    public List<ThreadType> addThreadType(List<ThreadType> listThreadType) {
        return tr.saveAll(listThreadType);
    }

    @Override
    public ThreadType updateThreadType(ThreadType t) {
        return tr.save(t);
    }

    @Override
    public List<ThreadType> updateThreadType(List<ThreadType> listThreadType) {
        return tr.saveAll(listThreadType);
    }

    @Override
    public void deleteThreadType(Long id) {
        tr.deleteById(id);
    }

    @Override
    public void deleteThreadType(ThreadType t) {
        tr.delete(t);
    }

    @Override
    public List<ThreadType> findAllThreadType() {
        return tr.findAll();
    }

    @Override
    public ThreadType findThreadTypeById(Long id) {
        return tr.findById(id).orElse(new ThreadType());
    }
}
