package com.example.lab7_20213745.config;


import com.example.lab7_20213745.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {

    final UsuarioRepository usuarioRepository;
    final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource, UsuarioRepository usuarioRepository) {
        this.dataSource = dataSource;
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Encriptación de contraseñas
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/login")  // Página de login
                .defaultSuccessUrl("/bienvenida", true)  // Redirige después de un login exitoso
                .failureUrl("/login?error")  // Redirige en caso de error en el login
                .permitAll()  // Permite el acceso a la página de login
                .and()
                .authorizeRequests()
                .antMatchers("/registro", "/registrar").permitAll()  // Permitir acceso a las páginas de registro
                .antMatchers("/admin/**").hasAuthority("admin")  // Rutas protegidas para el rol ADMIN
                .antMatchers("/gerente/**").hasAuthority("gerente")  // Rutas protegidas para el rol GERENTE
                .antMatchers("/cliente/**").hasAuthority("cliente")  // Rutas protegidas para el rol CLIENTE
                .anyRequest().authenticated()  // Cualquier otra solicitud requiere autenticación
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")  // Después de hacer logout, redirige al login
                .deleteCookies("JSESSIONID")  // Eliminar la cookie de sesión
                .invalidateHttpSession(true);  // Invalida la sesión actual

        return http.build();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        // SQL para la autenticación (obtener email, password, activo)
        String sqlAuth = "SELECT email, contrasena, activo FROM usuario WHERE email = ?";

        // SQL para la autorización (obtener el email y el nombre del rol)
        String sqlAuto = "SELECT u.email, r.nombre FROM usuario u " +
                "INNER JOIN rol r ON u.idrol = r.idrol " +
                "WHERE u.email = ?";

        users.setUsersByUsernameQuery(sqlAuth);
        users.setAuthoritiesByUsernameQuery(sqlAuto);

        return users;
    }
}

