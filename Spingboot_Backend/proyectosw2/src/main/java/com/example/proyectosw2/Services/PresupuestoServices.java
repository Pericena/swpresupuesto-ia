package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.PresupuestoDetalle;
import com.example.proyectosw2.Entity.PresupuestoEntity;
import com.example.proyectosw2.Repository.PresupuestoDetalleReposiroty;
import com.example.proyectosw2.Repository.PresupuestoRepository;
import com.example.proyectosw2.dto.PresupuestoCompleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresupuestoServices {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private PresupuestoDetalleReposiroty presupuestoDetalleRepository;

    public PresupuestoCompleto getPresupuestoCompletoByUsuarioId(Integer idUsuario) {
        List<PresupuestoEntity> presupuesto = presupuestoRepository.findByUsuarioId(idUsuario);
        List<PresupuestoDetalle> detalle = presupuestoDetalleRepository.findByUsuarioId(idUsuario.toString());
        return new PresupuestoCompleto(presupuesto, detalle);
    }
}
