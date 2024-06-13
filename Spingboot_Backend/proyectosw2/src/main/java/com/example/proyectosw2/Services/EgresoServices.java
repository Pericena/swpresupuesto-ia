package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.CategiriaEntity;
import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Repository.EgresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EgresoServices {

    @Autowired
    private EgresoRepository egresoRepository;

    public EgresoEntity getEgresoById(Integer id) {
        return egresoRepository.findById(id).orElse(null);
    }

    public EgresoEntity saveEgreso(EgresoEntity egreso) {
        return egresoRepository.save(egreso);
    }
    public List<EgresoEntity> getAllEgresos() {
        return egresoRepository.findAll();
    }
    public List<EgresoEntity> obtenerEgresosPorUsuario(Integer idUsuario) {
        return egresoRepository.findByCuentaID_UsuarioID(idUsuario);
    }
    public String  createEgreso( int id,  String concepto,  double monto,   String fechaEgreso, int cuentaID,int categoriaID){

        EgresoEntity egreso = egresoRepository.save(new EgresoEntity(id,concepto,monto,fechaEgreso, CuentaEntity.builder().id(cuentaID).build(), CategiriaEntity.builder().id(categoriaID).build())) ;
        if (egreso!= null){
            return "correcto";
        }return "incorrecto";

    }


}
