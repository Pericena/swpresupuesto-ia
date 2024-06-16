package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.PresupuestoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PresupuestoRepository extends MongoRepository<PresupuestoEntity, String> {
    @Query("{ 'idUsuario' : ?0 }")
    List<PresupuestoEntity> findByUsuarioId(Integer idUsuario);
}