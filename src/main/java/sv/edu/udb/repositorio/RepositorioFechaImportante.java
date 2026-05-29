package sv.edu.udb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.modelo.FechaImportante;

public interface RepositorioFechaImportante extends JpaRepository<FechaImportante, Long> {
}