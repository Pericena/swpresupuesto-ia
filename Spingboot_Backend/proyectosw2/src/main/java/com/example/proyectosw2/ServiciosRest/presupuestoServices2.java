package com.example.proyectosw2.ServiciosRest;

import com.example.proyectosw2.Entity.PresupuestoEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Repository.PresupuestoDetalleReposiroty;
import com.example.proyectosw2.Repository.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class presupuestoServices2 {
    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private PresupuestoDetalleReposiroty presupuestoDetalleRepository;


    public PresupuestoEntity createPresupuesto(int id , String nombre, String descripcion, double montoTotal,String fechaInicio,String fechaFin ,int idUsuario) {
        try {
            PresupuestoEntity nuevoPresupuesto = new PresupuestoEntity(id, idUsuario, nombre,descripcion,montoTotal,fechaInicio,fechaFin);
            return presupuestoRepository.save(nuevoPresupuesto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
