package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sv.edu.udb.modelo.Asistencia;
import sv.edu.udb.modelo.Estudiante;
import sv.edu.udb.servicio.ServicioAsistencia;
import sv.edu.udb.servicio.ServicioEstudiante;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;

@Controller
public class ControlAsistencia {

    @Autowired
    private ServicioAsistencia asistenciaService;

    @Autowired
    private ServicioEstudiante estudianteService;

    @GetMapping("/inicio-asistencia")
    public String inicio() {
        return "asistencia";
    }
    @GetMapping("/asistencia")
    public String asistencia(Model model) {

        model.addAttribute("students",
                estudianteService.getAllStudents());

        return "asistencia";
    }

    @GetMapping("/lista-asistencia")
    public String listaAsistencias(Model model) {
        model.addAttribute("asistencias", asistenciaService.listar());
        return "lista-asistencia";
    }

    @PostMapping("/guardarAsistencia")
    public String guardarAsistencia(
            @RequestParam(required = false) Long estudianteId,
            @RequestParam String estado) {

        if (estudianteId == null) {
            return "redirect:/asistencia";
        }

        Estudiante estudiante = estudianteService.getById(estudianteId);

        Asistencia asistencia = new Asistencia();

        asistencia.setEstudiante(estudiante);
        asistencia.setEstado(estado);
        asistencia.setFecha(LocalDate.now());

        asistenciaService.guardar(asistencia);

        return "redirect:/lista-asistencia";
    }
    @GetMapping("/salon")
    public String verSalon(
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) String section,
            Model model
    ) {

        if (grade == null || section == null) {
            return "salones";
        }

        List<Estudiante> students =
                estudianteService
                        .buscarPorGradeYSection(
                                String.valueOf(grade),
                                section
                        );

        model.addAttribute("students", students);
        model.addAttribute("grado", grade);
        model.addAttribute("seccion", section);

        return "salones";
    }
    @PostMapping("/salon/agregar")
    public String agregarEstudiante(
            @RequestParam String nombre,
            @RequestParam String grade,
            @RequestParam String section
    ) {

        Estudiante estudiante = new Estudiante();

        estudiante.setNombres(nombre);
        estudiante.setGrado(grade);
        estudiante.setSeccion(section);

        estudianteService.saveStudent(estudiante);

        return "redirect:/asistencia/" + grade + "/" + section;
    }

    @PostMapping("/guardarAsistenciaSalon")
    public String guardarAsistenciaSalon(
            @RequestParam Map<String,String> params
    ) {

        int grade =
                Integer.parseInt(params.get("grado"));

        String section =
                params.get("seccion");

        List<Estudiante> students =
                estudianteService
                        .buscarPorGradeYSection(String.valueOf(grade), section);

        for(Estudiante student : students){

            String estado =
                    params.get("estado_" + student.getId());

            Asistencia asistenciaExistente =
                    asistenciaService
                            .buscarPorFechaYEstudiante(
                                    LocalDate.now(),
                                    student
                            );

            if(asistenciaExistente != null){

                asistenciaExistente.setEstado(estado);

                asistenciaService
                        .guardar(asistenciaExistente);

            }else{

                Asistencia asistencia =
                        new Asistencia();

                asistencia.setEstudiante(student);
                asistencia.setEstado(estado);
                asistencia.setFecha(LocalDate.now());

                asistenciaService.guardar(asistencia);
            }
        }

        return "redirect:/salon?grade="
                + grade +
                "&section=" +
                section;
    }

}