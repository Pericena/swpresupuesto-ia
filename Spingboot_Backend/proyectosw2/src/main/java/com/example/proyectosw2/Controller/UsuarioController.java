package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.UsuarioEntity;

import com.example.proyectosw2.Services.UsuarioServices;
import com.example.proyectosw2.dto.AllDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @QueryMapping
    public UsuarioLoginResponse login(@Argument String email, @Argument String password) {
        Map<String, Object> response = usuarioServices.login(email, password);
        if (response != null) {
            UsuarioEntity usuario = (UsuarioEntity) response.get("usuario");
            String token = (String) response.get("token");
            return new UsuarioLoginResponse(usuario, token);
        } else {
            return null;
        }
    }

    @QueryMapping
    public AllDataResponse getAllData() {
    return usuarioServices.getAllData();
    }
    @QueryMapping
    public UsuarioEntity createUsuario(@Argument int id, @Argument String nombre,@Argument  String email,@Argument  String password) {
        try {
            UsuarioEntity nuevoUsuario = new UsuarioEntity(id, nombre, email, password);
            return usuarioServices.createUsuario(nuevoUsuario);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
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

    public static class UsuarioCreateResponse {
        private UsuarioEntity usuario;

        public UsuarioCreateResponse(UsuarioEntity usuario) {
            this.usuario = usuario;
        }

        // Getter and Setter for usuario
        public UsuarioEntity getUsuario() {
            return usuario;
        }

        public void setUsuario(UsuarioEntity usuario) {
            this.usuario = usuario;
        }
    }
}
