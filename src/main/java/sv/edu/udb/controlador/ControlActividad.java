package sv.edu.udb.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sv.edu.udb.modelo.Actividad;
import sv.edu.udb.servicio.ServicioActividad;

@Controller
@RequestMapping("/actividades")
public class ControlActividad {

    private final ServicioActividad actividadService;

    public ControlActividad(ServicioActividad actividadService) {
        this.actividadService = actividadService;
    }

    /**
     * Mostrar pantalla
     */
    @GetMapping
    public String index(Model model) {

        model.addAttribute(
                "actividades",
                actividadService.obtenerActividades());

        model.addAttribute(
                "actividad",
                new Actividad());

        return "actividades";
    }

    /**
     * Guardar actividad
     */
    @PostMapping("/save")
    public String guardarActividad(
            @ModelAttribute Actividad actividad) {

        actividadService.guardarActividad(actividad);

        return "redirect:/actividades";
    }

    /**
     * Eliminar actividad
     */
    @GetMapping("/delete/{id}")
    public String eliminarActividad(
            @PathVariable Long id) {

        actividadService.eliminar(id);

        return "redirect:/actividades";
    }
}