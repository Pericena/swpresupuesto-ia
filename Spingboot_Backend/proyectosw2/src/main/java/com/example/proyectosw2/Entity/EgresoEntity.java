package com.example.proyectosw2.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Egreso")
public class EgresoEntity {
    private String id;
    private String concepto;
    private String monto;
    private String fechaEgreso;
    private String CuentaID;
    private String CategoriaID;

}
