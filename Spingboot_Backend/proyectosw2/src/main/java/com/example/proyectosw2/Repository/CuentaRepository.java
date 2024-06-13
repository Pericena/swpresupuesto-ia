package com.example.proyectosw2.Repository;
import java.util.List;
import com.example.proyectosw2.Entity.CuentaEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CuentaRepository  extends MongoRepository<CuentaEntity,Integer> {
    List<CuentaEntity> findByUsuarioID(Integer usuarioID);

}
