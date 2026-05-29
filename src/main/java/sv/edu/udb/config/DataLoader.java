package sv.edu.udb.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import sv.edu.udb.modelo.Materia;
import sv.edu.udb.servicio.ServicioMateria;

@Component
public class DataLoader implements CommandLineRunner {

    private final ServicioMateria materiaService;

    public DataLoader(ServicioMateria materiaService) {
        this.materiaService = materiaService;
    }

    @Override
    public void run(String... args) {

        System.out.println("DATA LOADER EJECUTADO");

        /**
         * Evita insertar duplicados
         */
        if (materiaService.contarMaterias() == 0) {

            guardar("Matemática");
            guardar("Lenguaje");
            guardar("Ciencias");
            guardar("Estudios Sociales");
            guardar("Inglés");
            guardar("Informática");

            System.out.println("Materias cargadas correctamente");
        }
    }

    private void guardar(String nombre) {

        Materia materia = new Materia();

        materia.setNombre(nombre);

        materiaService.guardarMateria(materia);

        System.out.println("Materia insertada: " + nombre);
    }
}