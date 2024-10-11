package com.example.lab7_20213745.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "reservas")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservaId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "funcionId", nullable = false)
    private Funcion funcion;

    @Column(name = "asiento", nullable = false)
    private int asiento; // Número del asiento reservado
}
