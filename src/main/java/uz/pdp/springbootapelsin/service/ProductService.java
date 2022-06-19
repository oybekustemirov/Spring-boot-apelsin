package uz.pdp.springbootapelsin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.pdp.springbootapelsin.DTO.ProductDTO;
import uz.pdp.springbootapelsin.entity.Product;
import uz.pdp.springbootapelsin.repository.CategoryRepository;
import uz.pdp.springbootapelsin.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void getAll(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("list", productList);
    }

    public void add(Model model, ProductDTO productDTO){
        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(BigDecimal.valueOf(productDTO.getPrice()));
        product.setCategory(categoryRepository.getById(productDTO.getCatId()));

        productRepository.save(product);
        model.addAttribute("list", productRepository.findAll());
    }

    public Product getById(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }


}
