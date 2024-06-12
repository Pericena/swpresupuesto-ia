package com.example.proyectosw2.Controller;
import com.example.proyectosw2.Services.PresupuestoServices;
import com.example.proyectosw2.dto.PresupuestoCompleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // Importa esta anotación para mapear el ID del usuario
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PresupuestoController {

    @Autowired
    private PresupuestoServices presupuestoService;

    @GetMapping("/presupuestoCompleto/{idUsuario}")
    public PresupuestoCompleto getPresupuestoCompletoByUsuarioId(@PathVariable Integer idUsuario) { // Utiliza @PathVariable para mapear el ID del usuario
        return presupuestoService.getPresupuestoCompletoByUsuarioId(idUsuario);
    }
}
