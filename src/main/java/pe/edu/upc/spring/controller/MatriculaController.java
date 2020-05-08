package pe.edu.upc.spring.controller;

import java.util.Map;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Matricula;
import pe.edu.upc.spring.service.IMatriculaService;
import pe.edu.upc.spring.service.IAlumnoService;
import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.ICursoService;

@Controller
@RequestMapping("/matricula")
@SessionAttributes("matricula")
public class MatriculaController {

	@Autowired	
	private IMatriculaService maService;
	
	@Autowired	
	private IAlumnoService alumService;
	
	@Autowired
	private IDocenteService docService;
	
	@Autowired
	private ICursoService curService;
	
	@RequestMapping("/")
	public String irMatricula(Map<String, Object> model) {
		model.put("listaMatriculas", maService.listar());
		return "listMatriculas";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("matricula",new Matricula());
		model.addAttribute("listaAlumnos", alumService.listar());
		model.addAttribute("listaDocentes", docService.listar());
		model.addAttribute("listaCursos", curService.listar());
		return "matricula";		
	}	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Matricula objMatricula,
			BindingResult binRes,Model model) throws ParseException 
	{	
		if ( binRes.hasErrors() ) {
			model.addAttribute("listaAlumnos", maService.listar());
			return "matricula";
		}
		else {
			boolean flag = maService.insertar(objMatricula);
			if (flag) {
				return "redirect:/matricula/listar";
			}
			else {
				model.addAttribute("mensaje","Ocurrio un Error");
				return "redirect:/matricula/irRegistrar";
			}
		}				
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Matricula objMatricula, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		
		if (binRes.hasErrors()) {
			return "redirect://matricula/listar";		
		}
		else {		
			boolean flag = maService.modificar(objMatricula);
			if (flag) {			
				objRedir.addFlashAttribute("mensaje","se actualizo correctamente");
				return "redirect://matricula/listar";		
			}
			else {
				objRedir.addFlashAttribute("mensaje","Ocurrio un roche");
				return "redirect://matricula/listar";				
			}						
		}

	}
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
	
		Matricula objMatricula = maService.listarId(id);
		if ( objMatricula == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
			return "redirect://producto/listar";				
		} 
		else {		
			model.addAttribute("listaAlumnos", alumService.listar());
			model.addAttribute("listaDocentes", docService.listar());
			model.addAttribute("listaCursos", curService.listar());
			model.addAttribute("matricula", objMatricula);
			return "matricula";				
		}			
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, 
			@RequestParam(value ="id") Integer id) 
	{		
		if ( id != null && id>0) {				
			maService.eliminar(id);
			model.put("listaMatriculas",maService.listar());
		}						
		return "listMatriculas";
		
	} 
		
	@RequestMapping("/listar")
	public String listar(Map<String,Object> model) {
		model.put("listaMatriculas", maService.listar());
		return "listMatriculas";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, 
			@ModelAttribute Matricula matricula) 
	{	
		maService.listarId(matricula.getIdMatricula());
		return "listMatriculas";	
	}
	
	@RequestMapping("/buscar") /* permite controlar redirecciones */
	public String buscar(Map<String, Object> model, @ModelAttribute Matricula matricula) throws ParseException {

		List<Matricula> listaMatriculas;

		listaMatriculas = maService.findByMatricula(matricula.getSemestre());

		if (listaMatriculas.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaMatriculas", listaMatriculas);
		return "buscarmatricula";

	}

	@RequestMapping("/irBuscar") /* permite controlar redirecciones */
	public String irBuscar(Model model) {

		model.addAttribute("matricula", new Matricula());
		return "buscarmatricula";

	}

}
	

