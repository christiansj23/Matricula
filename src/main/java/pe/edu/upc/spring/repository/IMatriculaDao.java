package pe.edu.upc.spring.repository;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import pe.edu.upc.spring.model.Matricula;

@Repository
public interface IMatriculaDao extends JpaRepository <Matricula,Integer>{
	List<Matricula> findBySemestre(String semestre);
}