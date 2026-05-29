package sv.edu.udb.controlador;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import sv.edu.udb.modelo.Estudiante;
import sv.edu.udb.servicio.ServicioEstudiante;

@Controller
public class ControlEstudiante {

    private final ServicioEstudiante service;

    public ControlEstudiante(ServicioEstudiante service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("students", service.getAllStudents());

        if (!model.containsAttribute("student")) {
            model.addAttribute("student", new Estudiante());
        }

        return "index";
    }

    @PostMapping("/save")
    public String saveStudent(
            @Valid @ModelAttribute("student") Estudiante estudiante,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {

            model.addAttribute("students", service.getAllStudents());

            return "index";
        }

        service.saveStudent(estudiante);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        service.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {

        Estudiante estudiante = service.getStudentById(id);

        model.addAttribute("student", estudiante);

        model.addAttribute("students", service.getAllStudents());

        return "index";
    }
}