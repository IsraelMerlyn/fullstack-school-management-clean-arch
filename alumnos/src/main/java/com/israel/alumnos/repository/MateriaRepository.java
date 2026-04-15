package com.israel.alumnos.repository;

import com.israel.alumnos.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    List<Materia> findBySemestreId(Long semestreId);
}
