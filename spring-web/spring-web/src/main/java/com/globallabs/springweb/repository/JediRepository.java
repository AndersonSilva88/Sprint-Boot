package com.globallabs.springweb.repository;

import com.globallabs.springweb.model.Jedi;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JediRepository {
    private List<Jedi> jedi;

    public JediRepository(){
        jedi = new ArrayList<>();
        jedi.add(new Jedi("Luke", "Skywalker"));
    }

    public List<Jedi> getAllJedi(){
        return this.jedi;
    }

    public void add(final Jedi jedi) {
        this.jedi.add(jedi);
    }

    public List<Jedi> findAll() {
        return getAllJedi();
    }

    public Jedi findById(Long id) {
        return findById(id);
    }

    public void delete(Jedi jedi) {
    }

    public Object save(Jedi jedi) {
    }
}
