package pe.edu.upc.spring.service;

import java.util.List;


import pe.edu.upc.spring.model.Alumno;


public interface IAlumnoService {

	public boolean insertar(Alumno alumno);
	
	public boolean modificar(Alumno alumno);
	
	public void eliminar(int idAlumno);
	
	List<Alumno> findByAlumno(String nombreAlumno);
	
	List<Alumno> listar();
	
	public Alumno buscarPorId(int id);
		
	
}