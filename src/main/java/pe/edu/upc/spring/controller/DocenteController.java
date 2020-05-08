package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Docente;
import pe.edu.upc.spring.service.IDocenteService;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	
	@Autowired
	private IDocenteService docService;
	
	@RequestMapping("/listar")
	public String listar(Map<String,Object> model) {
		model.put("listaDocentes", docService.listar());
		return "listDocentes";
	}
	
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("docente",new Docente());
		return "docente";
		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Docente objDocente,BindingResult binRes,Model model) throws ParseException {
		
			if(binRes.hasErrors()) {
				return "docente";
			}
			else {
				boolean flag=docService.insertar(objDocente);
				if(flag) {
					return "redirect:/docente/listar";
				}
				else {
					model.addAttribute("mensaje","Ocurrio un Error");
					return "redirecte:/docente/irRegistrar";
				}
			}				
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Docente objDocente,BindingResult binRes,Model model, RedirectAttributes objRedir) throws ParseException {
		
	if (binRes.hasErrors()) {
	return "redirect://docente/listar";	
	
	}
	else {
		
		boolean flag = docService.modificar(objDocente);
		if(flag) {
			
			objRedir.addFlashAttribute("mensaje","se actualizo correctamente");
			return "redirect://docente/listar";	
			
		}
		
	
	else {
		objRedir.addFlashAttribute("mensaje","ocurrio un error");
		return "redirect://docente/listar";	
	}
		
	
	     }
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
	
		Docente objDocente =docService.listarId(id);
	if(objDocente == null) {
		objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		return "redirect://docente/listar";	
			
	} else {
		
		model.addAttribute("docente",objDocente);
		return "docente";
		
		
	}
	
	
	
}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value ="id") Integer id) {
		
		try {
		
			if(id!= null && id>0) {
				
				docService.eliminar(id);
				model.put("listaDocentes",docService.listar());
			}
			
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "no se puede eliminar el docente asignado");
			model.put("listarDocentes",docService.listar());

		}
		return "listDocentes";
		
	} 
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Docente docente) {
	
		docService.listarId(docente.getIdDocente());
		return "listDocente";
	}
	
	
	
		@RequestMapping("/buscar") /* permite controlar redirecciones */
		public String buscar(Map<String, Object> model, @ModelAttribute Docente docente) throws ParseException {

			List<Docente> listaDocentes;

			listaDocentes = docService.findByDocente(docente.getNombreDocente());

			if (listaDocentes.isEmpty()) {

				model.put("mensaje", "No se encontró");
			}

			model.put("listaDocentes", listaDocentes);
			return "buscardocente";

		}

		@RequestMapping("/irBuscar") /* permite controlar redirecciones */
		public String irBuscar(Model model) {

			model.addAttribute("docente", new Docente());
			return "buscardocente";

		}

	}