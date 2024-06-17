package com.example.proyectosw2.dto;


import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGastoResponse {
    private UsuarioEntity usuario;
    private List<EgresoEntity> egreso;
}
