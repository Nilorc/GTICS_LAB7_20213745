package com.example.lab7_20213745.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "salas")
@Getter
@Setter
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salaId")
    private Long id;

    @Column(name = "numero", nullable = false)
    private int numero; // Número de sala

    @Column(name = "capacidad", nullable = false)
    private int capacidad; // Capacidad de la sala

    @OneToMany(mappedBy = "sala")
    private List<Funcion> funciones;
}
