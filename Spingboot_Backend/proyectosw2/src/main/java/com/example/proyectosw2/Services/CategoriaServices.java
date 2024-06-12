package com.example.proyectosw2.Services;
import com.example.proyectosw2.Entity.CategiriaEntity;

import com.example.proyectosw2.Entity.UsuarioEntity;
import com.example.proyectosw2.Repository.CategiriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public CategiriaEntity createCategoria(int id , String nombre){
        try {
            CategiriaEntity newcategoria = new CategiriaEntity(id,nombre);
            return  categoriaRepository.save(newcategoria);
        }catch (Exception ex){
            return null;
        }




    }
}
