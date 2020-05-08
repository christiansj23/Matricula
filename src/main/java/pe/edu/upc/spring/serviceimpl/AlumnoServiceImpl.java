package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.repository.IAlumnoDao;
import pe.edu.upc.spring.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private IAlumnoDao dAlumno;

	@Override
	
	public boolean insertar(Alumno alumno) {

		Alumno objAlumno = dAlumno.save(alumno);
		if (objAlumno == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	
	public boolean modificar(Alumno alumno) {
		boolean flag = false;
		try {
			dAlumno.save(alumno);
			flag = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;

	}

	@Override
	
	public void eliminar(int idAlumno) {
		dAlumno.delete(idAlumno);		
	}

	@Override
	public List<Alumno> findByAlumno(String nombreAlumno) {
		return dAlumno.findByNombreAlumno(nombreAlumno);
	}


	@Override
	public List<Alumno> listar() {
		return dAlumno.findAll();
	}

	@Override
	public Alumno buscarPorId(int id) {
		return dAlumno.findOne(id);
	}

}