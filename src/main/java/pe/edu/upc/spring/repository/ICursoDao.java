package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Curso;
@Repository

public interface ICursoDao extends JpaRepository <Curso,Integer> {
	List<Curso> findByNombreCurso(String nombreCurso);
		
}
