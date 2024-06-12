package com.example.proyectosw2.Services;

import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Entity.IngresoEntity;
import com.example.proyectosw2.Repository.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngresoServices {

    @Autowired
    private IngresoRepository ingresoRepository;

    public IngresoEntity getIngresoById(String id) {
        return ingresoRepository.findById(id).orElse(null);
    }

    public IngresoEntity saveIngreso(IngresoEntity ingreso) {
        return ingresoRepository.save(ingreso);
    }

    public List<IngresoEntity> getAllIngresos() {
        return ingresoRepository.findAll();
    }

    public String  createIngreso( int id,  String concepto,  double monto,   String fechaEgreso, int cuentaID,int categoriaID){

        IngresoEntity ingreso = ingresoRepository.save(new IngresoEntity(id,concepto,monto,fechaEgreso,cuentaID,categoriaID)) ;
        if (ingreso!= null){
            return "correcto";
        }return "incorrecto";

}
}