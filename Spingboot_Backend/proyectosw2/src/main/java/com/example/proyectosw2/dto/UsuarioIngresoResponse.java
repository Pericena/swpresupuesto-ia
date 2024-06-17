package com.example.proyectosw2.dto;

import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Entity.IngresoEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioIngresoResponse {
    private UsuarioEntity usuario;
    private List<IngresoEntity> ingreso;

}
