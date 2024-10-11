package com.example.lab7_20213745.entity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolId")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre; // Roles como 'ADMIN', 'GERENTE', 'CLIENTE'

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;
}
