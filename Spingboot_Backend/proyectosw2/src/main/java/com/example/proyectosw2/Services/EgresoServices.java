package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.CategiriaEntity;
import com.example.proyectosw2.Entity.EgresoEntity;

import com.example.proyectosw2.Repository.CuentaRepository;
import com.example.proyectosw2.Repository.EgresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EgresoServices {

    @Autowired
    private EgresoRepository egresoRepository;

    public EgresoEntity Egreso(String id) {
        return egresoRepository.findByid(id).orElse(null);
    }
}
