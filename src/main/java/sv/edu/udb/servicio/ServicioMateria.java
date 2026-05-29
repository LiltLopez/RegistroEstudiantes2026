package sv.edu.udb.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import sv.edu.udb.modelo.Materia;
import sv.edu.udb.repositorio.RepositorioMateria;

@Service
public class ServicioMateria {

    private final RepositorioMateria repository;

    public ServicioMateria(RepositorioMateria repository) {
        this.repository = repository;
    }

    public List<Materia> obtenerMaterias() {
        return repository.findAll();
    }

    public Materia findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Materia no encontrada con ID: " + id));
    }

    public long contarMaterias() {
        return repository.count();
    }

    public void guardarMateria(Materia materia) {
        repository.save(materia);
    }
}