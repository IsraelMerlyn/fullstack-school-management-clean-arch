package com.israel.alumnos;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import java.util.Arrays;
import java.util.Optional;

import com.israel.alumnos.controllers.AlumnoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.israel.alumnos.model.Alumno;
import com.israel.alumnos.repository.AlumnoRepository;

@WebMvcTest(AlumnoController.class)
public class AlumnoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void debeTraerTodosLosAlumnos() throws Exception {

        Alumno alumno1 = new Alumno();
        alumno1.setId(1L);
        alumno1.setNombre("Israel");
        alumno1.setCarrera("Sistemas");

        Alumno alumno2 = new Alumno();
        alumno2.setId(2L);
        alumno2.setNombre("Juan");
        alumno2.setCarrera("Informatica");

        when(alumnoRepository.findAll()).thenReturn(Arrays.asList(alumno1, alumno2));

        mockMvc.perform(get("/alumnos/traer-alumnos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Israel")));
    }

    @Test
    public void debeInsertarUnAlumno() throws Exception {
        Alumno alumnoNuevo = new Alumno();
        alumnoNuevo.setNombre("ROLDAN");
        alumnoNuevo.setNumeroControl("22620100");

        when(alumnoRepository.save(org.mockito.ArgumentMatchers.any(Alumno.class))).thenReturn(alumnoNuevo);

        mockMvc.perform(post("/alumnos/insertar-alumnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoNuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("ROLDAN")));
    }
    @Test
    public void debeEliminarUnAlumno() throws Exception {
        Long idParaEliminar = 1L;

        mockMvc.perform(delete("/alumnos/eliminar-alumnos/{id}", idParaEliminar)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(alumnoRepository, times(1)).deleteById(idParaEliminar);
    }
}
