package com.elise.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.elise.model.Collecte;
import com.elise.service.CollecteService;

@Controller
public class CollecteController {

	private CollecteService collecteService;

	public static String makeUrl(HttpServletRequest request) {
		return request.getRequestURL().toString() + "?" + request.getQueryString();
	}

	@Autowired(required = true)
	@Qualifier(value = "collecteService")
	public void setCollecteService(CollecteService cs) {
		this.collecteService = cs;
	}

	@RequestMapping(value = "/listeCollectes", method = RequestMethod.GET)
	public String listeCollecte(Model model) {
		model.addAttribute("collecte", new Collecte());
		model.addAttribute("listCollect", this.collecteService.listCollect());
		return "listeCollectes";
	}

	public CollecteService getCollecteService() {
		return collecteService;
	}

	// Ajouter une collecte
	@RequestMapping(value = "/collecte/add", method = RequestMethod.POST)
	public ModelAndView  addCollecte(ModelAndView modelAndView, @ModelAttribute("collecteForm") Collecte p, HttpServletRequest request) {
		String idCollecte = (String) request.getSession().getAttribute("idCollecte");
		String typeCollecte = (String) request.getSession().getAttribute("typeCollecte");

		String[] chaine = idCollecte.split("_");
		int id = Integer.valueOf(chaine[0]);
		String nomSociete = chaine[1];
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();
		String dateToday = df.format(today);

		Collecte collecte = new Collecte(id, nomSociete, typeCollecte, dateToday, null);
		boolean resulat=this.collecteService.addCollect(collecte);
		 if (resulat) {
             modelAndView.addObject("successMessage", "La demande de la collecte a été effectuée avec succés");
         }
         modelAndView.setViewName("index");
         return modelAndView;

	}
	
	@RequestMapping("/{id}")
	public String addCollecte(@PathVariable("id") String id, HttpServletRequest request) {
		request.getSession().setAttribute("idCollecte", id);
		return "index";
	}

	@RequestMapping(value = "/collecte/validate/{id}", method = RequestMethod.POST)
	public String validateCollect(ModelAndView modelAndView, @ModelAttribute("validateForm") Collecte p, @PathVariable("id") int id) {
		this.collecteService.validateCollect(id);
         return "redirect:/listeCollectes";
	}

	@RequestMapping(value = "/collecte/type", method = RequestMethod.GET, params = { "argName" })
	public void getTypeCollecte(@RequestParam(value = "argName", required = true) String argName,
			HttpServletRequest request) {
		request.getSession().setAttribute("typeCollecte", argName);

	}

}
