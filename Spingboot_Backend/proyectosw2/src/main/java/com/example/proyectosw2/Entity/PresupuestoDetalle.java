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
@Document(collection = "PresupuestoDetalle")
public class PresupuestoDetalle {
    @Id
    private Integer id;
    private Double monto;
    @DBRef
    private CategiriaEntity idCategoria;
    @DBRef
    private PresupuestoEntity idPresupuesto;

}
