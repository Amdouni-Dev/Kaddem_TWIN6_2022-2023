package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ThreadRepository;
import esprit.tn.amdounidev.entities.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ThreadServiceImpl implements ThreadService{
    @Autowired
    ThreadRepository tr;

    @Autowired
    EtudiantService es;

    @Override
    public Thread addThread(Thread t) {
        Thread t1=new Thread();
        Thread t2=new Thread();
        long i = this.countThreads()+1;
       t1.setId(i);
       t2=tr.save(t);
       t1.setQuestion(""+t2.getId());
        tr.save(t1);
        t=switcher(t);
        t.setNb_likes(0);
        t.setPostDate(LocalDateTime.now());
        t.setUpdatedAt(LocalDateTime.now());
        return  t2;
    }

    @Override
    public List<Thread> addThread(List<Thread> listThread) {
        return (List<Thread>) tr.saveAll(listThread);
    }

    @Override
    public Thread updateThread(Thread t) {
        t=switcher(t);
        t.setUpdatedAt(LocalDateTime.now());
        return tr.save(t);
    }

    @Override
    public List<Thread> updateThread(List<Thread> listThread) {
        return (List<Thread>) tr.saveAll(listThread);
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
        return (List<Thread>) tr.findAll();
    }

    @Override
    public Thread findThreadById(Long id) {
        Thread thread = new Thread();
        if(id==99) {

            thread.setId(99);
            thread.setObject("aaaa");
        }
        return tr.findById(id).orElse(thread);
    }
    public  long countThreads(){
        return tr.countThreads();
    }


    @Column(name = "object")
    private String object;

    @Column(name = "nb_likes")
    private int nb_likes;

    @Column(name = "postDate")
    private LocalDateTime postDate;

    @Column(name = "display")
    private boolean display;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
/*
public Thread add2(String question,String object,boolean display,boolean verified,Long ide){
    Thread thread=new Thread();
    thread.setQuestion(question);
    thread.setObject(object);
    thread.setDisplay(display);
    thread.setVerified(verified);
    es.findEtudiantById(ide);
    tr.save(thread);
return thread;
}
*/





    // did like or not
    public String[] mots(){
        String[] l;
        l=this.findThreadById((long)99).getObject().split(" ");
        return l;
    }
    public String[] mots2(String t){
        String[] l = null;
        if(t==null){
            return l="x".split(" ");
        }
        l=t.split(" ");
        return l;
    }
    public boolean detector(String s1,String s2) {
        if(s1.equals(s2)) {

            return true;
        }

        return false;

    }




    public Thread switcher(Thread m) {
        String[] tokens=m.getQuestion().split(" ");
        String text = "";
        for(String s:tokens) {

            String[] mot=mots();
            String test=s;
            for(String ss:mot) {
                if(detector(s,ss)) {
                    test="****";
                }
            }
            text=text+" "+test;
        }

        m.setQuestion(text);
        return m;
    }



    //to do : it takes student id and returns the full name
    public String etudiantByName(Long idE){
        return  (es.findEtudiantById(idE).getPrenom()+" "+es.findEtudiantById(idE).getNom())

                ;}

    // to do : takes id thread and ide and check if this kid liked the post already check the first row of the thread table we made it for likes and all
    public boolean likedThread (Long idT,Long idE){
        mots2(tr.findlikeTh(""+idT).getObject());
        if(tr.findlikeTh(""+idT).getObject()!=null) {
            String[] tokens = tr.findlikeTh("" + idT).getObject().split(" ");
            for (String s : tokens) {
                if (detector(s, "" + idE)) {
                    return true;
                }

            }
            return false;
        }
        return false;
    }

    //bad words detector check and compare the 2nd Thread row


    public Thread like(Long idt,Long ide){
        if(!likedThread(idt,ide)) {
            String i = this.tr.findlikeTh("" + idt).getObject();
            Thread t = this.tr.findlikeTh("" + idt);
            String y = t.getQuestion().trim();
            t.setQuestion(y);
            t.setObject(i + " " + ide);
            return (this.updateThread(t));
        }
        return(new Thread());

    }


}
