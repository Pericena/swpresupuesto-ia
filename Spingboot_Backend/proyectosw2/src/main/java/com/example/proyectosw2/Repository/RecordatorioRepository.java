package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.PresupuestoEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface RecordatorioRepository extends MongoRepository<PresupuestoEntity, Integer> {
    Optional<PresupuestoEntity> findById(int id);
}
