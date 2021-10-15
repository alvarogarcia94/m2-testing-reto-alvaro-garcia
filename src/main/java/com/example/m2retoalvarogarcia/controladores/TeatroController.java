package com.example.m2retoalvarogarcia.controladores;


import com.example.m2retoalvarogarcia.repositories.TeatroRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeatroController {
    //Creación del repositorio
    TeatroRepository repository;

    //Constructor del propio repositorio
    public TeatroController(TeatroRepository repository) {
        this.repository = repository;
    }

    //Methods

    //CRUD

    //1. Data insertion (Create)

    @GetMapping("/insertar")
    public void addTheater(){


    }

    //2. Data retrieval (Retrieve)



    //3. Modifying data (Update)



    //4. Removing data (Delete)
}
