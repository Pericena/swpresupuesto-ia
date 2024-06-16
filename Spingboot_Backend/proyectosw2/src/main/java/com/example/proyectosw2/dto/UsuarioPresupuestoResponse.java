package com.example.proyectosw2.dto;

import com.example.proyectosw2.Entity.PresupuestoEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsuarioPresupuestoResponse {
    private UsuarioEntity usuario;
    private List<PresupuestoEntity> presupuestos;
}
