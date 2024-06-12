package com.example.proyectosw2.dto;

import com.example.proyectosw2.Entity.PresupuestoDetalle;
import com.example.proyectosw2.Entity.PresupuestoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresupuestoCompleto  {
    private PresupuestoEntity  presupuesto;
    private List<PresupuestoDetalle> detalle;
}
