package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.CategiriaEntity;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategiriaRepository extends MongoRepository<CategiriaEntity, String> {
    Optional<CategiriaEntity> findById(Integer id);
}