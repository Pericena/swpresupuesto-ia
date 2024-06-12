package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping("/crearuser")
    public ResponseEntity<String> createUsuario(@RequestParam int id, @RequestParam String nombre, @RequestParam String email, @RequestParam String password) {
        try {
            UsuarioEntity nuevoUsuario = usuarioServices.createUsuario(id, nombre, email, password);
            return ResponseEntity.ok("Usuario creado exitosamente con ID: " + nuevoUsuario.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body("Error al crear el usuario");
        }
    }
}
