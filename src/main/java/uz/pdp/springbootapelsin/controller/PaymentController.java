package uz.pdp.springbootapelsin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapelsin.entity.Customer;
import uz.pdp.springbootapelsin.service.CustomerService;
import uz.pdp.springbootapelsin.service.OrderService;
import uz.pdp.springbootapelsin.service.PaymentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderService orderService;
    private final CustomerService customerService;

    @GetMapping
    public String getAll(Model model) {
        paymentService.getAll(model);
        return "payment/list";
    }


    @GetMapping("/add")
    public String getAddPage() {
        return "payment/add"; //page nomi -> list.html templates
    }

    @PostMapping("/add")
    public String getSavePage(@ModelAttribute Customer customer, Model model) {
        customerService.add(model, customer);
        return "payment/list";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(value = "id") int id, Model model) {
        Customer customer =  customerService.getById(id);
        model.addAttribute("customer", customer);
        return "payment/update";
    }
    @GetMapping("/delete/{id}")
    public String deleteThroughId(@PathVariable(value = "id") int id,Model model) {
        customerService.deleteById(id);
        customerService.getAll(model);
        return "customer/list";

    }



}
