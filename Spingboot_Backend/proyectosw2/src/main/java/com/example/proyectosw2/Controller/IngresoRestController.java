package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Entity.IngresoEntity;
import com.example.proyectosw2.Services.EgresoServices;
import com.example.proyectosw2.Services.IngresoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class IngresoRestController {


    @Autowired
    private IngresoServices ingresoServices;


    @GetMapping("/listEgreso")
    public List<IngresoEntity> ListEgreso(@Argument String id) {
        return ingresoServices.getAllCategorias();
    }
}
