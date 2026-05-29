package sv.edu.udb.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(min = 3, max = 60, message = "Los nombres deben tener entre 3 y 60 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
            message = "Ingrese un nombre válido"
    )
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 3, max = 60, message = "Los apellidos deben tener entre 3 y 60 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
            message = "Ingrese apellidos válidos"
    )
    private String apellidos;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 4, message = "La edad mínima es 4")
    @Max(value = 25, message = "La edad máxima es 20")
    private int edad;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 5, max = 150, message = "La dirección es demasiado corta")
    private String direccion;

    @NotBlank(message = "El nombre del encargado es obligatorio")
    private String encargado;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(
            regexp = "^[0-9]{8}$",
            message = "El teléfono debe tener 8 números"
    )
    private String telefono;

    @NotBlank(message = "El grado es obligatorio")
    private String grado;

    @NotBlank(message = "La sección es obligatoria")
    private String seccion;

    @Column(length = 500)
    private String observacion;

}