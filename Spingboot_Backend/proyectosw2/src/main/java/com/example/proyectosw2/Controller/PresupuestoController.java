package com.example.proyectosw2.Controller;
import com.example.proyectosw2.Entity.PresupuestoEntity;
import com.example.proyectosw2.Services.PresupuestoServices;
import com.example.proyectosw2.dto.PresupuestoCompleto;
import com.example.proyectosw2.dto.UsuarioPresupuestoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PresupuestoController {

    @Autowired
    private PresupuestoServices presupuestoService;

    @GetMapping("/presupuestoCompleto/{idUsuario}")
    public List<PresupuestoCompleto> getPresupuestosCompletos(@PathVariable Integer idUsuario) {
        return presupuestoService.getPresupuestoCompletoByUsuarioId(idUsuario);
    }

    @PostMapping("/crearPresupuesto")
    public String crearPresupuesto(@RequestBody PresupuestoEntity presupuesto) {
        return presupuestoService.crearPresupuesto(presupuesto);
    }

    @GetMapping("/presupuestosUsuarios")
    public List<UsuarioPresupuestoResponse> getAllUsuariosConPresupuestos() {
        return presupuestoService.getAllUsuariosConPresupuestos();
    }


}
