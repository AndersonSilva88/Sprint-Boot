package com.globallabs.springweb.rest;

import com.globallabs.springweb.model.Jedi;
import com.globallabs.springweb.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class JediResource {

    @Autowired
    private JediRepository repository;

    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi() {

        return repository.findAll();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id, HttpResponse response) {
        final Optional<Jedi> jedi = repository.findById(id);
        if (jedi.isPresent()){
            return ResponseEntity.ok(jedi.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Validated @RequestBody Jedi jedi){
        return repository.save(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public Jedi upadateJedi(@PathVariable("id") Long id, @Validated @RequestBody Jedi dto){
        final Optional<Jedi> jediEntity = repository.findById(id);
        final Jedi jedi;

        if (jediEntity.isPresent()) {
                jedi = jediEntity.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        jedi.setName(dto.getName());
        jedi.setLastName(dto.getLastName());

        return ResponseEntity.ok(repository.save(jedi));
    }

    @DeleteMapping("/api/jedi/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        final Optional<Jedi> jedi = repository.findById(id);

        if (jedi.isPresent()){
            repository.delete(jedi.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
