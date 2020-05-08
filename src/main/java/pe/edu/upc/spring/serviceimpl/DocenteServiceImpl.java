package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Docente;

import pe.edu.upc.spring.repository.IDocenteDao;
import pe.edu.upc.spring.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {

	@Autowired
	private IDocenteDao dDocente;

	@Override
	@Transactional
	public boolean insertar(Docente docente) {

		Docente objDocente = dDocente.save(docente);
		if (objDocente == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Docente docente) {
		boolean flag = false;
		try {
			dDocente.save(docente);
			flag = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;

	}

	@Override
	@Transactional
	public void eliminar(int idDocente) {
		dDocente.delete(idDocente);		
	}



	@Override
	public Docente listarId(int idDocente) {
		return dDocente.findOne(idDocente);
	
	}
	
	@Override
	public List<Docente> listar() {
		return dDocente.findAll();
	}
	
	@Override
	public List<Docente> findByDocente(String nombreDocente) {
		return dDocente.findByNombreDocente(nombreDocente);
	}
	
}