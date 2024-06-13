package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.PresupuestoEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordatorioRepository extends MongoRepository<PresupuestoEntity, Integer> {
    Optional<PresupuestoEntity> findById(int id);
}
