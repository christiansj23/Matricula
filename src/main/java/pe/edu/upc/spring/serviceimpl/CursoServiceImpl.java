package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.repository.ICursoDao;
import pe.edu.upc.spring.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {

	@Autowired
	private ICursoDao dCurso;

	@Override
	@Transactional
	public boolean insertar(Curso curso) {

		Curso objCurso = dCurso.save(curso);
		if (objCurso == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Curso curso) {
		boolean flag = false;
		try {
			dCurso.save(curso);
			flag = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;

	}

	@Override
	@Transactional
	public void eliminar(int idCurso) {
		dCurso.delete(idCurso);		
	}

	@Override
	public Curso buscarId(int idCurso) {
		return dCurso.findOne(idCurso);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Curso> listar() {
		return dCurso.findAll();
	}

	

	@Override
	@Transactional(readOnly=true)
	public Curso listarId(int idCurso) {
		return dCurso.findOne(idCurso);
	
}
	@Override
	public List<Curso> findByCurso(String nombreCurso) {
		return dCurso.findByNombreCurso(nombreCurso);
	}
	
}