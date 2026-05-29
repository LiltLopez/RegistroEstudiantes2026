package sv.edu.udb.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import sv.edu.udb.modelo.Nota;
import sv.edu.udb.repositorio.RepositorioNota;

@Service
public class ServicioNota {

    private final RepositorioNota repository;

    public ServicioNota(RepositorioNota repository) {
        this.repository = repository;
    }

    public void guardarNota(Nota nota) {
        repository.save(nota);
    }

    public List<Nota> obtenerNota() {
        return repository.findAll();
    }
}