package com.example.lab7_20213745.entity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "funciones")
@Getter
@Setter
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "obraId", nullable = false)
    private Obra obra;

    @ManyToOne
    @JoinColumn(name = "salaId", nullable = false)
    private Sala sala;

    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;
}

