package sv.edu.udb.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sv.edu.udb.modelo.Actividad;
import sv.edu.udb.modelo.Estudiante;
import sv.edu.udb.modelo.Materia;
import sv.edu.udb.modelo.Nota;

import sv.edu.udb.servicio.ServicioActividad;
import sv.edu.udb.servicio.ServicioEstudiante;
import sv.edu.udb.servicio.ServicioMateria;
import sv.edu.udb.servicio.ServicioNota;

@Controller
public class ControlNota {

    private final ServicioNota notaService;
    private final ServicioEstudiante estudianteService;
    private final ServicioMateria materiaService;
    private final ServicioActividad actividadService;

    public ControlNota(

            ServicioNota notaService,
            ServicioEstudiante estudianteService,
            ServicioMateria materiaService,
            ServicioActividad actividadService) {

        this.notaService = notaService;
        this.estudianteService = estudianteService;
        this.materiaService = materiaService;
        this.actividadService = actividadService;
    }

    /**
     * Mostrar formulario
     */
    @GetMapping("/notas")
    public String index(Model model) {

        model.addAttribute(
                "notas",
                notaService.obtenerNota());

        model.addAttribute(
                "students",
                estudianteService.getAllStudents());

        model.addAttribute(
                "materias",
                materiaService.obtenerMaterias());

        model.addAttribute(
                "actividades",
                actividadService.obtenerActividades());

        model.addAttribute(
                "nota",
                new Nota());

        return "notas";
    }

    /**
     * Guardar nota
     */
    @PostMapping("/notas/save")
    public String guardarNotas(

            @ModelAttribute Nota nota,

            @RequestParam("estudianteId")
            Long estudianteId,

            @RequestParam("materiaId")
            Long materiaId,

            @RequestParam("actividadId")
            Long actividadId) {

        /**
         * Buscar entidades
         */
        Estudiante estudiante =
                estudianteService.findById(estudianteId);

        Materia materia =
                materiaService.findById(materiaId);

        Actividad actividad =
                actividadService.findById(actividadId);

        /**
         * Asociar relaciones
         */
        nota.setEstudiante(estudiante);

        nota.setMateria(materia);

        nota.setActividad(actividad);

        /**
         * Guardar
         */
        notaService.guardarNota(nota);

        return "redirect:/notas";
    }

}