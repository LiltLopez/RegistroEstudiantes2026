package sv.edu.udb.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.edu.udb.modelo.Asistencia;
import sv.edu.udb.modelo.Estudiante;
import sv.edu.udb.repositorio.RepositorioAsistencia;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioAsistencia {

    @Autowired
    private RepositorioAsistencia repository;

    public void guardar(Asistencia asistencia) {
        repository.save(asistencia);
    }

    public List<Asistencia> listar() {
        return repository.findAll();
    }

    public Optional<Asistencia> buscarPorFecha(
            Estudiante estudiante,
            LocalDate fecha
    ) {

        return repository.findByEstudianteAndFecha(
                estudiante,
                fecha
        );

    }
    public Asistencia buscarPorFechaYEstudiante(
            LocalDate fecha,
            Estudiante student
    ){
        return RepositorioAsistencia
                .findByFechaAndStudent(
                        fecha,
                        student
                );
    }
}