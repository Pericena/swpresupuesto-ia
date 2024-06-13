package com.example.proyectosw2.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.yaml.snakeyaml.events.Event;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Usuario")
public class UsuarioEntity {
    @Id
private Integer id;
private String nombre;
private String email;
private String password;

}
