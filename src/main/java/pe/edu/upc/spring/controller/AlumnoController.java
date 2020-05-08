package pe.edu.upc.spring.controller;


import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.support.SessionStatus;


import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Docente;
import pe.edu.upc.spring.service.IAlumnoService;
import pe.edu.upc.spring.service.IUploadFileService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	@Autowired
	private IAlumnoService alumService;
	
	
	@RequestMapping("/listar")
	public String listar(Map<String,Object> model) {
		model.put("listaAlumnos", alumService.listar());
		return "listAlumnos";
	}
	
	
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("alumno",new Alumno());
		return "alumno";
		
	}
	
	@RequestMapping("/registrar") /* permite controlar redirecciones */
	public String registrar(@ModelAttribute @Valid Alumno objAlumno,BindingResult binRes,Model model) throws ParseException {
		if (binRes.hasErrors()) {

			return "alumno";
		}
		else {
			boolean flag=alumService.insertar(objAlumno);
			if(flag) {
				return "redirect:/alumno/listar";
			}
			else {
				model.addAttribute("mensaje","Ocurrio un Error");
				return "redirecte:/alumno/irRegistrar";
			}
		}	
		
	}
	

	@RequestMapping("/actualizar") /* permite controlar redirecciones */
	public String actualizar(@ModelAttribute @Valid Alumno objAlumno, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/alumno/listar";
		} else {
			boolean flag = alumService.modificar(objAlumno);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/alumno/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/alumno/listar";
			}
		}
	}

	@RequestMapping("/modificar/{id}") /* permite controlar redirecciones */
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Alumno objAlumno = alumService.buscarPorId(id);
		if (objAlumno == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/alumno/listar";
		} else {
		
			model.addAttribute("alumno", objAlumno);
			return "alumno";

		}
	}
	

	
	@RequestMapping("/eliminar") /* permite controlar redirecciones */
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		if (id != null && id > 0) {
			alumService.eliminar(id);
			model.put("listaAlumnos", alumService.listar());
		}
		return "listAlumnos";
	}

	
	@RequestMapping("/buscar") /* permite controlar redirecciones */
	public String buscar(Map<String, Object> model, @ModelAttribute Alumno alumno) throws ParseException {

		List<Alumno> listaAlumnos;

		listaAlumnos = alumService.findByAlumno(alumno.getNombreAlumno());

		if (listaAlumnos.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaAlumnos", listaAlumnos);
		return "buscaralumno";

	}

	@RequestMapping("/irBuscar") /* permite controlar redirecciones */
	public String irBuscar(Model model) {

		model.addAttribute("alumno", new Alumno());
		return "buscaralumno";

	}

}