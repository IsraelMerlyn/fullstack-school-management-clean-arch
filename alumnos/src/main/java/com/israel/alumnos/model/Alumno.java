package com.israel.alumnos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El número de control es obligatorio")
    @Size(min = 8, max = 10, message = "El número de control debe tener entre 8 y 10 caracteres")
    private String numeroControl;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe ser de exactamente 10 dígitos numéricos")
    private String telefono;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;
    @NotBlank(message = "La carrera es obligatoria")
    private String carrera;
    private String imagenURL;



}
