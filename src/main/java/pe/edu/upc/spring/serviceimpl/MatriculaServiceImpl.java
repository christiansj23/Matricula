package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.model.Matricula;
import pe.edu.upc.spring.repository.IMatriculaDao;
import pe.edu.upc.spring.service.IMatriculaService;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

	@Autowired
	private IMatriculaDao dMatricula;

	@Override
	@Transactional
	public boolean insertar(Matricula matricula) {

		Matricula objMatricula = dMatricula.save(matricula);
		if (objMatricula == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Matricula matricula) {
		boolean flag = false;
		try {
			dMatricula.save(matricula);
			flag = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;

	}

	@Override
	@Transactional
	public void eliminar(int idMatricula) {
		dMatricula.delete(idMatricula);		
	}
	
	@Override
	public List<Matricula> findByMatricula(String semestre) {
		return dMatricula.findBySemestre(semestre);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Matricula> listar() {
		return dMatricula.findAll();
	}

	
	@Override
	@Transactional(readOnly=true)
	public Matricula listarId(int idMatricula) {
		return dMatricula.findOne(idMatricula);
	
}
	
}