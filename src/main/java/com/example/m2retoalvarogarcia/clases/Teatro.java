package com.example.m2retoalvarogarcia.clases;

import javax.persistence.Id;

public class Teatro {

    //Attributes
    @Id
    private Long id;
    private String nombre;
    private String localidad;
    private Integer year;


    //Builders
    public Teatro(){}

    public Teatro(Long id, String nombre, String localidad, Integer year) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
        this.year = year;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    // 2String

    @Override
    public String toString() {
        return "Teatro{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                ", year=" + year +
                '}';
    }
}
