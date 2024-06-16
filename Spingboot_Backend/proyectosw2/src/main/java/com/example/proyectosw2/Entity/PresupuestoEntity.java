package com.example.proyectosw2.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Presupuesto")
public class PresupuestoEntity {
    @Id
    private Integer id;
    private Integer idUsuario;
    private String nombre;
    private String descripcion;
    private Double montoTotal;
    private String fechaInicio;
    private String fechaFin;

}
