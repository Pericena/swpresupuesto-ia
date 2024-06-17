package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.CategiriaEntity;
import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Services.CategoriaServices;
import com.example.proyectosw2.Services.CuentaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CuentaRestController {

    @Autowired
    private CuentaServices cuentaServices;

    @PostMapping("/crearCuenta")
    public String createCategoria(@RequestBody CuentaEntity cuenta) {

        if( cuentaServices.crearCuenta(cuenta)){
            return "categoria creada correctamente";
        }
        return "fallo al crear categoria";

    }
    @GetMapping("/listCuenta")
    public List<CuentaEntity> listCategoria(@Argument String id) {
        return cuentaServices.getAllCategorias();
    }


}
