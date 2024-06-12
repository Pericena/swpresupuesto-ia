package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.PresupuestoDetalle;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PresupuestoDetalleReposiroty extends MongoRepository<PresupuestoDetalle, String> {
    @Query("{ 'idUsuario' : ?0 }")
    List<PresupuestoDetalle> findByUsuarioId(String idUsuario);
}
