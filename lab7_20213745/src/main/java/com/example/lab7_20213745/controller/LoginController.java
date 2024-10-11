package com.example.lab7_20213745.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Muestra la página de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";  // Devuelve la vista login.html
    }

    // Redirige en caso de error en las credenciales
    @GetMapping("/login-error")
    public String mostrarErrorLogin(Model model) {
        model.addAttribute("error", "Credenciales incorrectas");
        return "login";  // Devuelve la vista login.html con el mensaje de error
    }
}

