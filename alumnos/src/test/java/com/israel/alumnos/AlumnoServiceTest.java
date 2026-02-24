package com.israel.alumnos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.israel.alumnos.model.Alumno;
import com.israel.alumnos.repository.AlumnoRepository;
import com.israel.alumnos.services.AlumnoService;

@ExtendWith(MockitoExtension.class) // Habilita el uso de anotaciones de Mockito
public class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository; // Simulamos el Repositorio

    @InjectMocks
    private AlumnoService alumnoService; // Inyectamos el mock DENTRO del Servicio real

    @Test
    public void debeGuardarUnAlumno() {
        // ARRANGE
        Alumno alumnoParaGuardar = new Alumno();
        alumnoParaGuardar.setNombre("Carlos");
        alumnoParaGuardar.setCarrera("Informatica");

        Alumno alumnoGuardado = new Alumno();
        alumnoGuardado.setId(1L); // Simulamos que la BD le asignó un ID
        alumnoGuardado.setNombre("Carlos");
        alumnoGuardado.setCarrera("Informatica");

        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumnoGuardado);

        // ACT
        Alumno resultado = alumnoService.guardarAlumno(alumnoParaGuardar);

        // ASSERT
        assertNotNull(resultado.getId()); // Verificamos que ya tiene ID
        assertEquals("Carlos", resultado.getNombre());
        verify(alumnoRepository, times(1)).save(alumnoParaGuardar);
    }

    @Test
    public void debeActualizarUnAlumnoSiExiste() {
        // ARRANGE
        Long id = 1L;
        Alumno alumnoExistente = new Alumno(id, "111", "ViejoNombre", "Apellido", "123", "email", "Carrera", "url");
        Alumno datosNuevos = new Alumno(null, "111", "NuevoNombre", "Apellido", "123", "email", "Carrera", "url");

        // Simulamos que la base de datos SÍ encuentra al alumno
        when(alumnoRepository.findById(id)).thenReturn(Optional.of(alumnoExistente));
        // Simulamos el guardado
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(datosNuevos);

        // ACT
        Optional<Alumno> resultado = alumnoService.actualizarAlumno(id, datosNuevos);

        // ASSERT
        assertTrue(resultado.isPresent());
        assertEquals("NuevoNombre", resultado.get().getNombre());
    }
}