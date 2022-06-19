package uz.pdp.springbootapelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.pdp.springbootapelsin.DTO.OrderDTO;
import uz.pdp.springbootapelsin.entity.Detail;
import uz.pdp.springbootapelsin.entity.Invoice;
import uz.pdp.springbootapelsin.entity.Order;
import uz.pdp.springbootapelsin.repository.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    public void getOne(Model model){
        model.addAttribute("customerList",customerRepository.findAll());
        model.addAttribute("productList",productRepository.findAll());
    }

    public void save(Model model, OrderDTO orderDTO){
//        System.out.println(orderDto);
        Order order = new Order();
        order.setCustomer(customerRepository.getById(orderDTO.getCustomerId()));
        order.setDate(new Date());
        Order saveOrder = orderRepository.save(order);

        Detail detail = new Detail();
        detail.setOrder(saveOrder);
        detail.setProduct(productRepository.getById(orderDTO.getProductId()));
        detail.setQuantity(orderDTO.getQuantity());
        Detail saveDetail = detailRepository.save(detail);



        BigDecimal price = saveDetail.getProduct().getPrice();
        short quntity = saveDetail.getQuantity();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,3);
        Date date3 = new Date();
        date3=calendar.getTime();
        Invoice invoice = Invoice.builder()
                .order(saveOrder)
                .amount(price.multiply(BigDecimal.valueOf(quntity)))
                .issued(date)
                .due(date3)
                .build();
        invoiceRepository.save(invoice);

//        model.addAttribute("list",orderRepository.findAll());
    }

    public void deleteById(int id){
        int detailId = 0;
        for (Detail detail : detailRepository.findAll()) {
            if (detail.getOrder().getId()==id) {
                detailId=detail.getId();
            }
        }
        int invoiceId = 0;
        for (Invoice invoice : invoiceRepository.findAll()) {
            if (invoice.getOrder().getId()==id) {
                invoiceId=invoice.getId();
            }
        }
        detailRepository.deleteById(detailId);
        invoiceRepository.deleteById(invoiceId);
        orderRepository.deleteById(id);
    }
}
