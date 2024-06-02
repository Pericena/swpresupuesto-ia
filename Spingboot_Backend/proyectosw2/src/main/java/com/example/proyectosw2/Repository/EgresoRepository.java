package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EgresoRepository  extends MongoRepository<EgresoEntity,String> {
    Optional<EgresoEntity> findByid(String CuentaID);
}
