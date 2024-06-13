package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.PresupuestoDetalle;
import com.example.proyectosw2.Entity.PresupuestoEntity;
import com.example.proyectosw2.Repository.PresupuestoDetalleReposiroty;
import com.example.proyectosw2.Repository.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresupuestoDetalleServices {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private PresupuestoDetalleReposiroty presupuestoDetalleRepository;


    public String crearPresupuestoDetalle(PresupuestoDetalle presupuestoDetalle) {

        PresupuestoDetalle  newPresupuestoDetalle= presupuestoDetalleRepository.save(presupuestoDetalle);
        if (newPresupuestoDetalle == null){
            return "error";
        }
        return "exito";

    }
}
