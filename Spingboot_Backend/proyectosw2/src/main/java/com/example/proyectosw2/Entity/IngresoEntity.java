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
@Document(collection = "Ingreso")
public class IngresoEntity {
    @Id
    private Integer id;
    private String concepto;
    private Double monto;
    private String fechaIngreso;
    @DBRef
    private CuentaEntity cuentaID;
    @DBRef
    private CategiriaEntity categoriaID;
}
