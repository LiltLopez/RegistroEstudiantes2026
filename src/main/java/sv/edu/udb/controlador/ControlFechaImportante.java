package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sv.edu.udb.modelo.FechaImportante;
import sv.edu.udb.servicio.ServicioFechaImportante;

@Controller
public class ControlFechaImportante {

    @Autowired
    private ServicioFechaImportante servicio;

    @GetMapping("/fechas")
    public String listarFechas(Model model) {

        model.addAttribute("fechas", servicio.listar());
        model.addAttribute("fechaImportante", new FechaImportante());

        return "fechas";
    }

    @PostMapping("/guardarFecha")
    public String guardarFecha(@ModelAttribute FechaImportante fecha) {

        servicio.guardar(fecha);

        return "redirect:/fechas";
    }

    @GetMapping("/eliminarFecha/{id}")
    public String eliminarFecha(@PathVariable Long id) {

        servicio.eliminar(id);

        return "redirect:/fechas";
    }
}
