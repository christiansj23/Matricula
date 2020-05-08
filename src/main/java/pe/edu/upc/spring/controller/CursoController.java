package pe.edu.upc.spring.controller;

import java.util.Map;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

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

import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.IDocenteService;


@Controller
@RequestMapping("/curso")
@SessionAttributes("curso")
public class CursoController {

	@Autowired	
	private ICursoService curService;
	
	@Autowired	
	private IDocenteService docService;
	
	
	@RequestMapping("/")
	public String irCurso(Map<String, Object> model) {
		model.put("listaCursos", curService.listar());
		return "listCursos";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("curso",new Curso());
		model.addAttribute("listaDocentes", docService.listar());
		return "curso";		
	}	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Curso objCurso,
			BindingResult binRes,Model model) throws ParseException 
	{	
		if ( binRes.hasErrors() ) {
			model.addAttribute("listaDocentes", docService.listar());
			return "curso";
		}
		else {
			boolean flag = curService.insertar(objCurso);
			if (flag) {
				return "redirect:/curso/listar";
			}
			else {
				model.addAttribute("mensaje","Ocurrio un Error");
				return "redirect:/curso/irRegistrar";
			}
		}				
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Curso objCurso, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		
		if (binRes.hasErrors()) {
			return "redirect://Curso/listar";		
		}
		else {		
			boolean flag = curService.modificar(objCurso);
			if (flag) {			
				objRedir.addFlashAttribute("mensaje","se actualizo correctamente");
				return "redirect://curso/listar";		
			}
			else {
				objRedir.addFlashAttribute("mensaje","Ocurrio un roche");
				return "redirect://curso/listar";				
			}						
		}

	}
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
	
		Curso objCurso = curService.listarId(id);
		if ( objCurso == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
			return "redirect://curso/listar";				
		} 
		else {		
			model.addAttribute("listaDocentes", docService.listar());
			model.addAttribute("curso", objCurso);
			return "curso";				
		}			
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, 
			@RequestParam(value ="id") Integer id) 
	{		
		if ( id != null && id>0) {				
			curService.eliminar(id);
			model.put("listaCursos",curService.listar());
		}						
		return "listCursos";
		
	} 
		
	@RequestMapping("/listar")
	public String listar(Map<String,Object> model) {
		model.put("listaCursos", curService.listar());
		return "listCursos";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, 
			@ModelAttribute Curso curso) 
	{	
		curService.listarId(curso.getIdCurso());
		return "listCursos";	
	}
	
	@RequestMapping("/buscar") /* permite controlar redirecciones */
	public String buscar(Map<String, Object> model, @ModelAttribute Curso curso) throws ParseException {

		List<Curso> listaCursos;

		listaCursos = curService.findByCurso(curso.getNombreCurso());

		if (listaCursos.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaCursos", listaCursos);
		return "buscarcurso";

	}

	@RequestMapping("/irBuscar") /* permite controlar redirecciones */
	public String irBuscar(Model model) {

		model.addAttribute("curso", new Curso());
		return "buscarcurso";

	}

}