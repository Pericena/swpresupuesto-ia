package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.PresupuestoDetalle;
import com.example.proyectosw2.Entity.PresupuestoEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Repository.PresupuestoDetalleReposiroty;
import com.example.proyectosw2.Repository.PresupuestoRepository;
import com.example.proyectosw2.Repository.UsuarioRepository;
import com.example.proyectosw2.dto.PresupuestoCompleto;
import com.example.proyectosw2.dto.UsuarioPresupuestoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PresupuestoServices {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private PresupuestoDetalleReposiroty presupuestoDetalleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PresupuestoCompleto> getPresupuestoCompletoByUsuarioId(Integer idUsuario) {
        List<PresupuestoEntity> presupuestos = presupuestoRepository.findByUsuarioId(idUsuario);
        List<PresupuestoCompleto> presupuestosCompletos = new ArrayList<>();

        for (PresupuestoEntity presupuesto : presupuestos) {
            List<PresupuestoDetalle> detalles = presupuestoDetalleRepository.findByIdPresupuesto(presupuesto.getId());
            PresupuestoCompleto presupuestoCompleto = new PresupuestoCompleto(presupuesto, detalles);
            presupuestosCompletos.add(presupuestoCompleto);
        }

        return presupuestosCompletos;
    }

    public String crearPresupuesto(PresupuestoEntity presupuesto) {
        PresupuestoEntity newPresupuesto = presupuestoRepository.save(presupuesto);
        if (newPresupuesto == null) {
            return "error";
        }
        return "exito";
    }

    public List<UsuarioPresupuestoResponse> getAllUsuariosConPresupuestos() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        List<UsuarioPresupuestoResponse> response = new ArrayList<>();

        for (UsuarioEntity usuario : usuarios) {
            List<PresupuestoEntity> presupuestos = presupuestoRepository.findByUsuarioId(usuario.getId());
            response.add(new UsuarioPresupuestoResponse(usuario, presupuestos));
        }

        return response;
    }
}
