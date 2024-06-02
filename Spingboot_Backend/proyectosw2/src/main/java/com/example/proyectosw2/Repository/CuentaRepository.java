package com.example.proyectosw2.Repository;
import java.util.List;
import com.example.proyectosw2.Entity.CuentaEntity;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface CuentaRepository  extends MongoRepository<CuentaEntity,String> {
    List<CuentaEntity> findByUsuarioID(String usuarioID);
}
