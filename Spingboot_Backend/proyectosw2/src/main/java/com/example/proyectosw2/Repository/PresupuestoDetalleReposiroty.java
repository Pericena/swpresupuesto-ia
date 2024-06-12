package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.PresupuestoDetalle;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PresupuestoDetalleReposiroty extends MongoRepository<PresupuestoDetalle, String> {
    List<PresupuestoDetalle> findByIdPresupuesto(Integer idPresupuesto);
}
