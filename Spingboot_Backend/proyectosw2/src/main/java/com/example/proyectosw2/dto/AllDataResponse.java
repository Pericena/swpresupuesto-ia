package com.example.proyectosw2.dto;


import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Entity.IngresoEntity;
import com.example.proyectosw2.Entity.EgresoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllDataResponse {
    private List<CuentaEntity> cuentas;
    private List<IngresoEntity> ingresos;
    private List<EgresoEntity> egresos;
}