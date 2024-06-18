package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.*;
import com.example.proyectosw2.Repository.EgresoRepository;
import com.example.proyectosw2.Repository.IngresoRepository;
import com.example.proyectosw2.Repository.UsuarioRepository;
import com.example.proyectosw2.dto.UsuarioGastoResponse;
import com.example.proyectosw2.dto.UsuarioIngresoResponse;
import com.example.proyectosw2.dto.UsuarioPresupuestoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EgresoServices {

    @Autowired
    private EgresoRepository egresoRepository;
    @Autowired
    private IngresoRepository ingresoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public List<UsuarioIngresoResponse> getAllUsuariosConGasto() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        List<UsuarioIngresoResponse> response = new ArrayList<>();

        for (UsuarioEntity usuario : usuarios) {
            List<IngresoEntity> gastos = ingresoRepository.findByCuentaID_UsuarioID(usuario.getId());
            response.add(new UsuarioIngresoResponse(usuario, gastos));
        }

        return response;
    }



}
