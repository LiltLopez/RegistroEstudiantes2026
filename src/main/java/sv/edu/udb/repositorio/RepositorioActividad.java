package sv.edu.udb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.edu.udb.modelo.Actividad;

public interface RepositorioActividad
        extends JpaRepository<Actividad, Long> {

}