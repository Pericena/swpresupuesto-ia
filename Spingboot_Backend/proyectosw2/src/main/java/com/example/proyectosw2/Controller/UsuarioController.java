package com.example.proyectosw2.Controller;


import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller

public class UsuarioController {
    @Autowired
    private UsuarioServices usuario;

    @QueryMapping
    public UsuarioEntity login(@Argument String email, @Argument String password){
        return usuario.Login(email,password);
    }

}
