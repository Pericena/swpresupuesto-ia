package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.PresupuestoEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Services.UsuarioServices;
import com.example.proyectosw2.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping("/crearuser")
    public ResponseEntity<String> createUsuario(@RequestBody UsuarioEntity Usuario) {
        try {
            UsuarioEntity nuevoUsuario = usuarioServices.createUsuario(Usuario);
            return ResponseEntity.ok("Usuario creado exitosamente con ID: " + nuevoUsuario.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body("Error al crear el usuario");
        }
    }


    @PostMapping("/Login")
    public ResponseEntity<UsuarioLoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> response = usuarioServices.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (response != null) {
            UsuarioEntity usuario = (UsuarioEntity) response.get("usuario");
            String token = (String) response.get("token");
            return ResponseEntity.ok(new UsuarioLoginResponse(usuario, token));
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }


    public static class UsuarioLoginResponse {
        private UsuarioEntity usuario;
        private String token;

        public UsuarioLoginResponse(UsuarioEntity usuario, String token) {
            this.usuario = usuario;
            this.token = token;
        }

        // Getters y Setters
        public UsuarioEntity getUsuario() {
            return usuario;
        }

        public void setUsuario(UsuarioEntity usuario) {
            this.usuario = usuario;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}


