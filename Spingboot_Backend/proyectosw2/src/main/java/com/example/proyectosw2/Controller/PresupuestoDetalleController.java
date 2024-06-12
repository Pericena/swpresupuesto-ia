package com.example.proyectosw2.Controller;


import com.example.proyectosw2.Entity.PresupuestoDetalle;
import com.example.proyectosw2.Entity.PresupuestoEntity;
import com.example.proyectosw2.Services.PresupuestoDetalleServices;
import com.example.proyectosw2.Services.PresupuestoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PresupuestoDetalleController {

    @Autowired
    private PresupuestoDetalleServices presupuestoDetalleServices;


    @PostMapping("/crearPresupuestoDetalle")
    public String crearPresupuesto(@RequestBody PresupuestoDetalle presupuestoDetalle) {
        return presupuestoDetalleServices.crearPresupuestoDetalle(presupuestoDetalle);
    }

}
