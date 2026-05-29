package sv.edu.udb.modelo;

import jakarta.persistence.*;

@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    /**
     * Constructor vacío
     */
    public Actividad() {
    }

    /**
     * GETTERS Y SETTERS
     */

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}