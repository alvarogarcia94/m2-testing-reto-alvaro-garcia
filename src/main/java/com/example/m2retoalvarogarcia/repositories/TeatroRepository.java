package com.example.m2retoalvarogarcia.repositories;

import com.example.m2retoalvarogarcia.clases.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeatroRepository extends JpaRepository<Teatro,Long> {

    List<Teatro> findByName(String nombre);

    List<Teatro> findByLocationAndName(String localidad, String nombre);

}
