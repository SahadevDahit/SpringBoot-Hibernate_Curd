package com.dahit.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dahit.demo.entity.Employee;
import com.dahit.demo.repository.repository;

@Controller
public class empController {
    @Autowired
    repository empRepo;

    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("listEmployees", employees);
        System.out.println(employees);
        return "index";
    }

    // to show adding record html
    @GetMapping("/addemployees")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    // to save record
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        empRepo.save(employee);
        return "redirect:/";
    }

    // for updatebyId
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Optional<Employee> optional = empRepo.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
            model.addAttribute("employee", employee);
            return "update_employee";
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
    }

    // to delete record drom database
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method
        empRepo.deleteById(id);
        return "redirect:/";
    }

    // pathvariable

    @GetMapping("/url/{id}")
    public String singlePathVariable(@PathVariable("id") int id, Model model) {

        model.addAttribute("id", id);
        return "url";
    }

    // Req Query
    @GetMapping("/url")
    public String Reqparam(@RequestParam("name") String name, Model model) {

        // model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "url";
    }
}
