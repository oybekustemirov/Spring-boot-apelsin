package uz.pdp.springbootapelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.pdp.springbootapelsin.entity.Category;
import uz.pdp.springbootapelsin.entity.Customer;
import uz.pdp.springbootapelsin.repository.CategoryRepository;
import uz.pdp.springbootapelsin.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public void getAll(Model model) {
        List<Customer> customerList = customerRepository.findAll();
        model.addAttribute("list", customerList);
    }

    public void add(Model model, Customer customer) {
        customerRepository.save(customer);
        model.addAttribute("list", customerRepository.findAll());
    }

    public void edit(Integer id, Model model, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
            Customer editedCustomer = optionalCustomer.get();
            editedCustomer.setName(customer.getName());
            customerRepository.save(editedCustomer); // ma'lumot qo'shish
        }
        model.addAttribute("list", customerRepository.findAll(Sort.by("id")));
    }


    public Customer getById(Integer id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id) {
            customerRepository.deleteById(id);
        }
    }

