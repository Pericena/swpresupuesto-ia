package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.IngresoEntity;
import com.example.proyectosw2.Services.IngresoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class IngresoController {

    @Autowired
    private IngresoServices ingresoServices;

    @QueryMapping
    public IngresoEntity ingreso(@Argument String id) {
        return ingresoServices.getIngresoById(id);
    }



    @MutationMapping
    public String createIngreso(@Argument  int id, @Argument  String concepto, @Argument  double monto,@Argument    String fechaEgreso, @Argument  int cuentaID, @Argument int categoriaID) {
        return ingresoServices.createIngreso(id,concepto,monto,fechaEgreso,cuentaID,categoriaID);
    }
}
