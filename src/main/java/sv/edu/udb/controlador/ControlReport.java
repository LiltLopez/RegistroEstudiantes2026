package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sv.edu.udb.repositorio.RepositorioAsistencia;
import sv.edu.udb.repositorio.RepositorioEstudiante;
import sv.edu.udb.repositorio.RepositorioFechaImportante;

@Controller
public class ControlReport {

    @Autowired
    private RepositorioEstudiante estudianteRepo;

    @Autowired
    private RepositorioAsistencia asistenciaRepo;

    @Autowired
    private RepositorioFechaImportante fechaRepo;

    @GetMapping("/report")
    public String report(Model model) {

        long totalEstudiantes = estudianteRepo.count();

        long totalAsistencias = asistenciaRepo.count();

        long presentes = asistenciaRepo.findAll()
                .stream()
                .filter(a -> a.getEstado().equalsIgnoreCase("Presente"))
                .count();

        long ausentes = asistenciaRepo.findAll()
                .stream()
                .filter(a -> a.getEstado().equalsIgnoreCase("Ausente"))
                .count();

        long totalFechas = fechaRepo.count();

        model.addAttribute("totalEstudiantes", totalEstudiantes);
        model.addAttribute("totalAsistencias", totalAsistencias);
        model.addAttribute("presentes", presentes);
        model.addAttribute("ausentes", ausentes);
        model.addAttribute("totalFechas", totalFechas);

        model.addAttribute("students", estudianteRepo.findAll());
        model.addAttribute("asistencias", asistenciaRepo.findAll());

        return "report";
    }
}