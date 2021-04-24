package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;

	@GetMapping
	public String displayEmployeeList(Model model) {
		List<Employee> employees = empRepo.findAll();
		System.out.println("employees" + employees.toString());
		model.addAttribute("employeesList", employees);
		return "employees/list-employees";

	}

	@GetMapping("/new")
	public String displayEmployee(Model model) {
		Employee aemployee = new Employee();

		model.addAttribute("employee", aemployee);
		return "employees/new-employee";

	}

	@PostMapping("/save")
	public String showEmployee(Employee employee, Model model) {

		empRepo.save(employee);
		return "redirect:/employees/new";
	}

}
