package uz.pdp.springbootapelsin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapelsin.entity.Category;
import uz.pdp.springbootapelsin.entity.Customer;
import uz.pdp.springbootapelsin.repository.CustomerRepository;
import uz.pdp.springbootapelsin.service.CategoryService;
import uz.pdp.springbootapelsin.service.CustomerService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;


    @GetMapping
    public String getAll(Model model){
        customerService.getAll(model);
        return "customer/list"; //page nomi -> list.html templates
    }

    @GetMapping("/add")
    public String getAddPage() {
//        model.addAttribute("category", null);
        return "customer/add"; //page nomi -> list.html templates
    }

    @PostMapping("/add")
    public String getSavePage(@ModelAttribute Customer customer, Model model) {
        customerService.add(model, customer);
        return "customer/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model){
       Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditPage(@PathVariable Integer id, @ModelAttribute Customer customer, Model model) {
        customerService.edit(id, model, customer);
        return "customer/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteThroughId(@PathVariable(value = "id") int id,Model model) {
        customerService.deleteById(id);
        customerService.getAll(model);
        return "customer/list";

    }
}
