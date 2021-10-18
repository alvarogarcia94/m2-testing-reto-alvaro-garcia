package com.example.m2retoalvarogarcia.controladores;


import com.example.m2retoalvarogarcia.clases.Teatro;
import com.example.m2retoalvarogarcia.repositories.TeatroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeatroController {
    //Creación del repositorio
    TeatroRepository repository;

    //Constructor del propio repositorio
    public TeatroController(TeatroRepository repository) {
        this.repository = repository;
    }

    //Methods
    @GetMapping("/theaters")
    public List<Teatro> findAll(){
        return repository.findAll();
    }

    //CRUD

    //1. Data insertion (Create)
    @GetMapping("/theaters/insertion")
    public void inserts(){
        //Teatro theater = new Teatro(null, "", "",);
        //repository.save(theater);
    }


    //2. Data retrieval (Retrieve)
    @GetMapping("/theaters/theater{nombre}")
    public List<Teatro> findByName(@PathVariable String teatro){
        return repository.findByNombre(teatro);
    }


    //3. Modifying data (Update)
    @PutMapping("/theaters")
    public void update(@RequestBody Teatro teatro){
        if (teatro.getId() != null){
            repository.save(teatro);
        }
    }


    //4. Removing data (Delete)
    @DeleteMapping("/theaters/{id}")
    public void deleteById(@PathVariable Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
        }
    }

    @DeleteMapping("/theathers")
    public void delete(@PathVariable Teatro teatro){
        repository.deleteAll();
    }

}