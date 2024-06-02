package com.example.proyectosw2.Controller;


import com.example.proyectosw2.Entity.CategiriaEntity;
import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Services.EgresoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;

public class EgresoController {
    @Autowired
    private EgresoServices egresoServices;

    @QueryMapping
    public EgresoEntity Egreso(@Argument String id) {
        return egresoServices.Egreso(id);
    }
}
