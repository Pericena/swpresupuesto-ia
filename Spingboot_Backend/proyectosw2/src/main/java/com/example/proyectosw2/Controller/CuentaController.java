package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Services.CategoriaServices;
import com.example.proyectosw2.Services.CuentaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;

public class CuentaController {
    @Autowired
    private CuentaServices cuentaServices;

    public List<CuentaEntity> getCuentaById(@Argument String id){
        return cuentaServices.buscarPorUsuarioID(id);
    }

}
