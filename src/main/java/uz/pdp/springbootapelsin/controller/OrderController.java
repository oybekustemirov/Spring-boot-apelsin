package uz.pdp.springbootapelsin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.springbootapelsin.DTO.OrderDTO;
import uz.pdp.springbootapelsin.repository.CategoryRepository;
import uz.pdp.springbootapelsin.repository.CustomerRepository;
import uz.pdp.springbootapelsin.repository.OrderRepository;
import uz.pdp.springbootapelsin.repository.ProductRepository;
import uz.pdp.springbootapelsin.service.OrderService;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderService orderService;

    @GetMapping
    public String getListPage(Model model) {
        model.addAttribute("list", orderRepository.findAll());
        return "order/list";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        orderService.getOne(model);
        return "order/add"; //page nomi -> list.html templates
    }

    @PostMapping("/add")
    public String savePage(Model model, OrderDTO orderDTO){
        orderService.save(model, orderDTO);
        model.addAttribute("list",orderRepository.findAll());
        return "redirect:";
    }
}
