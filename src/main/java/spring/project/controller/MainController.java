package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.project.model.Person;
import spring.project.service.PersonService;

@Controller
public class MainController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value={"/", "/person"}, method=RequestMethod.GET)
	public String listPersons(Model model){
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons",personService.listPersons());
		return "Person";
	}
	
	@RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	public String removePerson(@PathVariable("id") int id){
		personService.removePerson(id);
		return "redirect:/person";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editPerson(@PathVariable("id") int id, Model model){
		model.addAttribute("person", personService.getPersonById(id));
		model.addAttribute("listPersons", personService.listPersons());
		return "Person";
	}
	
	@RequestMapping(value="/person/add", method=RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person person){
		personService.addPerson(person);
		return "redirect:/person";
	}
	
}
