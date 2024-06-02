package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.CategiriaEntity;
import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CuentaServices {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<CuentaEntity> buscarPorUsuarioID(String usuarioID) {
        return cuentaRepository.findByUsuarioID(usuarioID);
    }
}