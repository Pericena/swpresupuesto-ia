package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.IngresoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IngresoRepository extends MongoRepository<IngresoEntity, String> {
    Optional<IngresoEntity> findById(String id);
}
