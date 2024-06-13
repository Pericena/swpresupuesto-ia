package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Services.EgresoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EgresoController {

    @Autowired
    private EgresoServices egresoServices;

    @QueryMapping
    public EgresoEntity egreso(@Argument Integer id) {
        return egresoServices.getEgresoById(id);
    }

    @MutationMapping
    public String createEgreso(@Argument  int id, @Argument  String concepto, @Argument  double monto,@Argument    String fechaEgreso, @Argument  int cuentaID, @Argument int categoriaID) {
  return egresoServices.createEgreso(id,concepto,monto,fechaEgreso,cuentaID,categoriaID);
    }
}
