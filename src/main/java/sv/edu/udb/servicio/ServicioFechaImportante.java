package sv.edu.udb.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.modelo.FechaImportante;
import sv.edu.udb.repositorio.RepositorioFechaImportante;

import java.util.List;

@Service
public class ServicioFechaImportante {

    @Autowired
    private RepositorioFechaImportante repository;

    public List<FechaImportante> listar() {
        return repository.findAll();
    }

    public void guardar(FechaImportante fecha) {
        repository.save(fecha);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}