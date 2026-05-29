package sv.edu.udb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.modelo.Estudiante;

import java.util.List;

public interface RepositorioEstudiante extends JpaRepository<Estudiante, Long> {

    List<Estudiante> findByGrado(String grado);

    List<Estudiante> findBySeccion(String seccion);

    List<Estudiante> findByGradoAndSeccion(String grado, String seccion);

    List<Estudiante> findByNombresContaining(String nombres);

}