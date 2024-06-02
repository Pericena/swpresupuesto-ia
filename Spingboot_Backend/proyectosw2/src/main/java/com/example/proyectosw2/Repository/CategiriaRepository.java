package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.CategiriaEntity;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategiriaRepository extends MongoRepository<CategiriaEntity, String> {
    Optional<CategiriaEntity> findById(String id);
}