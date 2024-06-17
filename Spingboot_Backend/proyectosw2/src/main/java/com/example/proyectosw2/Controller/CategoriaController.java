package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.CategiriaEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Services.CategoriaServices;
import com.example.proyectosw2.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
@Controller
public class CategoriaController {
    @Autowired
    private CategoriaServices categoriaServices;

    @QueryMapping
    public CategiriaEntity getCategoriaById(@Argument String id) {
        return categoriaServices.Categoria(id);
    }

}