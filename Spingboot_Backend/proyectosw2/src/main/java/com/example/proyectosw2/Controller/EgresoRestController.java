package com.example.proyectosw2.Controller;

import com.example.proyectosw2.Entity.CuentaEntity;
import com.example.proyectosw2.Entity.EgresoEntity;
import com.example.proyectosw2.Services.CuentaServices;
import com.example.proyectosw2.Services.EgresoServices;
import com.example.proyectosw2.dto.UsuarioGastoResponse;
import com.example.proyectosw2.dto.UsuarioIngresoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class EgresoRestController {

    @Autowired
    private EgresoServices egresoServices;

    @GetMapping("/listaIngresoByUsuaio")
    public List<UsuarioIngresoResponse> listGastobyUsuario() {
        return egresoServices.getAllUsuariosConGasto();
    }

}
