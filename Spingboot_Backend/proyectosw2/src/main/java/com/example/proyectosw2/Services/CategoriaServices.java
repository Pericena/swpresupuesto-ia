package com.example.proyectosw2.Services;
import com.example.proyectosw2.Entity.CategiriaEntity;

import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Repository.CategiriaRepository;
import com.example.proyectosw2.Repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServices {

    @Autowired
    private CategiriaRepository categoriaRepository;

    public CategiriaEntity Categoria(String id) {
        return categoriaRepository.findById(id).orElse(null);
    }
    public List<CategiriaEntity> getAllCategorias() {
        return categoriaRepository.findAll();
    }
    public CategiriaEntity createCategoria(CategiriaEntity nuevaCategoria){
        try {
            CategiriaEntity newcategoria =nuevaCategoria;
            return  categoriaRepository.save(newcategoria);
        }catch (Exception ex){
            return null;
        }



    }
}
