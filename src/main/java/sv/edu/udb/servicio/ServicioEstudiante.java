package sv.edu.udb.servicio;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sv.edu.udb.modelo.Asistencia;
import sv.edu.udb.modelo.Nota;
import sv.edu.udb.modelo.Estudiante;
import sv.edu.udb.repositorio.RepositorioAsistencia;
import sv.edu.udb.repositorio.RepositorioNota;
import sv.edu.udb.repositorio.RepositorioEstudiante;

import java.util.List;

@Service
public class ServicioEstudiante {

    private final RepositorioEstudiante repository;
    private final RepositorioAsistencia asistenciaRepository;
    private final RepositorioNota notaRepository;

    public ServicioEstudiante(
            RepositorioEstudiante repository,
            RepositorioAsistencia asistenciaRepository,
            RepositorioNota notaRepository
    ) {
        this.repository = repository;
        this.asistenciaRepository = asistenciaRepository;
        this.notaRepository = notaRepository;
    }

    public List<Estudiante> getAllStudents() {
        return repository.findAll();
    }

    public void saveStudent(Estudiante estudiante) {
        repository.save(estudiante);
    }

    public Estudiante findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    @Transactional
    public void deleteById(Long id) {

        Estudiante estudiante = findById(id);

        List<Asistencia> asistencias =
                asistenciaRepository.findByEstudiante(estudiante);

        asistenciaRepository.deleteAll(asistencias);

        List<Nota> notas =
                notaRepository.findByEstudiante(estudiante);

        notaRepository.deleteAll(notas);

        repository.deleteById(id);
    }

    public void deleteStudent(Long id) {
        deleteById(id);
    }

    public List<Estudiante> buscarPorNombre(String nombre) {
        return repository.findByNombresContaining(nombre);
    }

    public Estudiante getStudentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Estudiante> buscarPorGradeYSection(String grado, String seccion) {
        return repository.findByGradoAndSeccion(grado, seccion);
    }

    public Estudiante getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}