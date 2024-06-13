package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.*;
import com.example.proyectosw2.Services.CategoriaServices;
import com.example.proyectosw2.Services.CuentaServices;
import com.example.proyectosw2.Services.EgresoServices;
import com.example.proyectosw2.Services.IngresoServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoriaRestController {

    @Autowired
    private CategoriaServices categoriaServices;

    @PostMapping("/crearCategoria")
    public String createCategoria(@RequestBody CategiriaEntity categoria) {

            if( categoriaServices.createCategoria(categoria)!= null){
                return "categoria creada correctamente";
            }
            return "fallo al crear categoria";

    }
    @PostMapping("/listaCategoria/EgresoIngreso")
    public String ListCategoriaEgresoIngreso(@RequestBody CategiriaEntity categoria) {

        if( categoriaServices.createCategoria(categoria)!= null){
            return "categoria creada correctamente";
        }
        return "fallo al crear categoria";

    }
    @GetMapping("/usuario/{idUsuario}/detalle")
    public String obtenerDetalleUsuario(@PathVariable Integer idUsuario) {


        return "true";
    }
}
