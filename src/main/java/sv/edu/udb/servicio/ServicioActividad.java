package sv.edu.udb.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import sv.edu.udb.modelo.Actividad;
import sv.edu.udb.repositorio.RepositorioActividad;

@Service
public class ServicioActividad {

    private final RepositorioActividad repository;

    public ServicioActividad(RepositorioActividad repository) {
        this.repository = repository;
    }

    /**
     * Obtener todas las actividades
     */
    public List<Actividad> obtenerActividades() {
        return repository.findAll();
    }

    /**
     * Guardar actividad
     */
    public void guardarActividad(Actividad actividad) {
        repository.save(actividad);
    }

    /**
     * Buscar actividad por ID
     */
    public Actividad findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Eliminar actividad
     */
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}