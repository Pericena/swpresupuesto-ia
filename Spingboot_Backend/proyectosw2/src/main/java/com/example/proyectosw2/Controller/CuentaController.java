package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Services.CategoriaServices;
import com.example.proyectosw2.Services.CuentaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class CuentaController {
    @Autowired
    private CuentaServices cuentaServices;

    //para Obtener Solo el get
    @QueryMapping
    public List<CuentaEntity> getCuentaById(@Argument Integer usuarioID){
        return cuentaServices.buscarPorUsuarioID(usuarioID);
    }
//para hacer insertar eliminar y actualizar , crud
    @MutationMapping
    //poner argument a todos los parametros
    public String  createCuenta(CuentaEntity cuenta){
        if (cuentaServices.crearCuenta(cuenta) ){
        return "exito al crear cuenta";
        }

        return "Fallo algo";

    }
}
