package sv.edu.udb.modelo;

import jakarta.persistence.*;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nota obtenida
     */
    private Double nota;

    /**
     * RELACION ESTUDIANTE
     */
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    /**
     * RELACION MATERIA
     */
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    /**
     * RELACION ACTIVIDAD
     */
    @ManyToOne
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;

    /**
     * Constructor
     */
    public Nota() {

        this.estudiante = new Estudiante();
        this.materia = new Materia();
        this.actividad = new Actividad();

    }

    /**
     * GETTERS Y SETTERS
     */

    public Long getId() {
        return id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

}