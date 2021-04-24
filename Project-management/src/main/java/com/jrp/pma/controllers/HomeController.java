package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.springExample.Car;

@Controller
public class HomeController {
	
	@Autowired
	Car car;
	

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		Map<String, Object> map= new HashMap<>();
		
		List<Project> projects =proRepo.findAll();
		model.addAttribute("projectsList" ,projects);
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		
		model.addAttribute("projectStatusCnt", jsonString);
		
		List<EmployeeProject> projectCount = empRepo.employeeProjects();
		System.out.println("projectCount"+projectCount);
		model.addAttribute("employeesListProjectCnt" , projectCount);
		
		return "main/home";
		
		
	}
}
