package com.example.proyectosw2.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Recordatorio")
public class RecordatorioEntity {
    @Id
    private Integer  id;
    private String Concepto;
    private String fecha;

}
