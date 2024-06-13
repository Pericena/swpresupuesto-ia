package com.example.proyectosw2.Repository;

import com.example.proyectosw2.Entity.IngresoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IngresoRepository extends MongoRepository<IngresoEntity, String> {
    Optional<IngresoEntity> findById(String id);
    List<IngresoEntity> findByCuentaID_UsuarioID(Integer idUsuario);

}
