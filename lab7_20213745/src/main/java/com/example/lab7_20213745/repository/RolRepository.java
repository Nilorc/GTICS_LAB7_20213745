package com.example.lab7_20213745.repository;


import com.example.lab7_20213745.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);  // Busca roles como 'CLIENTE', 'GERENTE', 'ADMIN'
}
