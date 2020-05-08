package pe.edu.upc.spring.service;

import java.util.List;


import pe.edu.upc.spring.model.Curso;

public interface ICursoService {

	public boolean insertar(Curso curso);
	
	public boolean modificar(Curso curso);
	
	public void eliminar(int idCurso);
	
	public Curso buscarId(int idCurso);
	
	public Curso listarId(int idCurso);
	
	List<Curso> listar();
	
	List<Curso> findByCurso(String nombreCurso);
	
}