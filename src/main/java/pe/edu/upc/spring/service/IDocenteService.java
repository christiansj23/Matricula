package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Docente;

public interface IDocenteService {

	public boolean insertar(Docente docente);
	
	public boolean modificar(Docente docente);
	
	public void eliminar(int idDocente);
	
	public Docente listarId(int idDocente);
	
	List<Docente> listar();
	
	List<Docente> findByDocente(String nombreDocente);

}
