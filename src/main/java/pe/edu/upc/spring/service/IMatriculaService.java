package pe.edu.upc.spring.service;

import java.util.List;


import pe.edu.upc.spring.model.Matricula;

public interface IMatriculaService {

	public boolean insertar(Matricula matricula);
	
	public boolean modificar(Matricula matricula);
	
	public void eliminar(int idMatricula);

	
	public Matricula listarId(int idMatricula);
	
	List<Matricula> listar();
	
	List<Matricula> findByMatricula(String semestre);
	
	
}