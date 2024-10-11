package com.example.lab7_20213745.controller;


import com.example.lab7_20213745.entity.Usuario;
import com.example.lab7_20213745.entity.Rol;
import com.example.lab7_20213745.repository.RolRepository;
import com.example.lab7_20213745.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Muestra la página de registro
    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";  // Devuelve la vista registro.html
    }

    // Maneja el formulario de registro
    @PostMapping("/registrar")
    public String registrarUsuario(@RequestParam("nombre") String nombre,
                                   @RequestParam("apellido") String apellido,
                                   @RequestParam("correo") String correo,
                                   @RequestParam("contrasena") String contrasena,
                                   @RequestParam("rol") String rolNombre,
                                   Model model) {

        // Verifica si el correo ya está registrado
        if (usuarioRepository.findByCorreo(correo) != null) {
            model.addAttribute("error", "Este correo ya está registrado.");
            return "registro";  // Devuelve la vista de registro con un mensaje de error
        }

        // Crea un nuevo usuario con los datos proporcionados
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);  // Se usa el campo nombre
        usuario.setApellido(apellido);  // Se usa el campo apellido
        usuario.setCorreo(correo);
        usuario.setContrasena(passwordEncoder.encode(contrasena));  // Encripta la contraseña

        // Asigna el rol
        Rol rol = rolRepository.findByNombre(rolNombre);  // Busca el rol en la base de datos
        usuario.setRol(rol);

        // Guarda el usuario en la base de datos
        usuarioRepository.save(usuario);

        model.addAttribute("mensaje", "Registro exitoso. Ahora puedes iniciar sesión.");
        return "redirect:/login";  // Redirige al formulario de login
    }
}
