package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Services.CategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoriaRestController {

    @Autowired
    private CategoriaServices categoriaServices;

    @PostMapping("/crearCategoria")
    public String createCategoria(@RequestParam int id, @RequestParam String nombre, @RequestParam String email, @RequestParam String password) {

            if( categoriaServices.createCategoria(id, nombre )!= null){
                return "categoria creada correctamente";
            }
            return "fallo al crear categoria";

    }
}
