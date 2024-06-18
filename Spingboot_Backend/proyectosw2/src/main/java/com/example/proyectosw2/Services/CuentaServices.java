package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.CategiriaEntity;
import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CuentaServices {

    @Autowired
    private CuentaRepository cuentaRepository;



    public List<CuentaEntity> buscarPorUsuarioID(Integer usuarioID) {
        return cuentaRepository.findByUsuarioID(usuarioID);
    }
    public List<CuentaEntity> obtenerCuentasPorUsuario(Integer idUsuario) {
        return cuentaRepository.findByUsuarioID(idUsuario);
    }

    public List<CuentaEntity> getAllCategorias() {
        return cuentaRepository.findAll();
    }

    //crear cuenta
    public boolean crearCuenta(CuentaEntity cuenta) {
        try {
            cuentaRepository.save(cuenta);
            return true;
        }catch (Exception ex){
            return false;
        }

    }
}