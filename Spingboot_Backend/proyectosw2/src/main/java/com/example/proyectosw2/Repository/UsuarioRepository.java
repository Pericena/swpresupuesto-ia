package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface  UsuarioRepository extends MongoRepository<UsuarioEntity,String>{
    Optional<UsuarioEntity>findByEmail( String email);
}

