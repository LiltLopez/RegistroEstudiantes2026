package sv.edu.udb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.edu.udb.modelo.Nota;
import sv.edu.udb.modelo.Estudiante;
import java.util.List;

public interface RepositorioNota extends JpaRepository<Nota, Long> {

    List<Nota> findByEstudiante(Estudiante estudiante);

}
