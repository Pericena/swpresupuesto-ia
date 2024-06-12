package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Entity.IngresoEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Repository.CuentaRepository;
import com.example.proyectosw2.Repository.EgresoRepository;
import com.example.proyectosw2.Repository.IngresoRepository;
import com.example.proyectosw2.Repository.UsuarioRepository;
import com.example.proyectosw2.dto.AllDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service

public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private IngresoRepository ingresoRepository;
    @Autowired
    private EgresoRepository egresoRepository;



    @Autowired
    private TokenService tokenService;

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

    public AllDataResponse getAllData(){
      List <CuentaEntity> cuenta =cuentaRepository.findAll();
      List <IngresoEntity> ingreso =ingresoRepository.findAll();
        List <EgresoEntity> egreso =egresoRepository.findAll();

        AllDataResponse response= new AllDataResponse(cuenta,ingreso,egreso);
        return response;




    }
    public Map<String, Object> login(String email, String password) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario == null) {
            return null;
        }
        if (Objects.equals(usuario.getPassword(), password) && Objects.equals(usuario.getEmail(), email)) {
            String token = tokenService.generateToken(email);
            Map<String, Object> response = new HashMap<>();
            response.put("usuario", usuario);
            response.put("token",token);
            return response;
        } else {
            return null;
        }
    }
    public UsuarioEntity createUsuario(UsuarioEntity newUsuario) {
        try {
            UsuarioEntity nuevoUsuario =newUsuario;
            return usuarioRepository.save(nuevoUsuario);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



}
