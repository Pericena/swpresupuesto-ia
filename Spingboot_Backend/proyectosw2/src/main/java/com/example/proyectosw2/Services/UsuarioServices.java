package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service

public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String Login2(String email,String Password){
        UsuarioEntity usuario=usuarioRepository.findByEmail(email).orElse(null);
        if (usuario==null){
            return "no existe el usuario";
        }
        if (Objects.equals(usuario.getPassword(), Password) && Objects.equals(usuario.getEmail(), email)){

            return "usuario Correcto";
        }else {
            return "contraseña o email incorrecto";
        }

    }
    public UsuarioEntity Login(String email,String Password){
        UsuarioEntity usuario=usuarioRepository.findByEmail(email).orElse(null);
        if (usuario==null){
            return null;
        }
        if (Objects.equals(usuario.getPassword(), Password) && Objects.equals(usuario.getEmail(), email)){

            return usuario;
        }else {
            return null;
        }

    }
}
