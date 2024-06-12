package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.EgresoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EgresoRepository extends MongoRepository<EgresoEntity, String> {
    Optional<EgresoEntity> findById(String id);
}
