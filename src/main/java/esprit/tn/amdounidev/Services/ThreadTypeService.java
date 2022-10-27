package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.ThreadType;

import java.util.List;

public interface ThreadTypeService {

    ThreadType addThreadType(ThreadType d);
    List<ThreadType> addThreadType (List<ThreadType> listThreadType);

    ThreadType updateThreadType (ThreadType d);
    List<ThreadType> updateThreadType (List<ThreadType> listThreadType);

    void deleteThreadType(Long id);
    void deleteThreadType(ThreadType d);

    List<ThreadType> findAllThreadType();
    ThreadType findThreadTypeById (Long id);
}
