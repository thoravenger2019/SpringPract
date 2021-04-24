package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	

	@GetMapping
	public String displayProjectList(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("ProjectsList", projects);
		return "projects/list-projects";

	}

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aproject = new Project();
		List<Employee> employees = empRepo.findAll();
		
		model.addAttribute("project", aproject);
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";
	}

	@PostMapping("/save")
	public String displayresult(Project project, Model model) {
		
		proRepo.save(project);
		
		return "redirect:/projects";

	}
}
