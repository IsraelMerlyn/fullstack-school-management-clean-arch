package com.israel.alumnos.repository;
import com.israel.alumnos.model.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SemestreRepository extends JpaRepository<Semestre, Long>{
}
