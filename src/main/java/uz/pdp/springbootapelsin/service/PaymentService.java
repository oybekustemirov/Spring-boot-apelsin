package uz.pdp.springbootapelsin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.springbootapelsin.DTO.PaymentDTO;
import uz.pdp.springbootapelsin.entity.Invoice;
import uz.pdp.springbootapelsin.entity.Order;
import uz.pdp.springbootapelsin.entity.Payment;
import uz.pdp.springbootapelsin.repository.InvoiceRepository;
import uz.pdp.springbootapelsin.repository.OrderRepository;
import uz.pdp.springbootapelsin.repository.PaymentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private  final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final InvoiceRepository invoiceRepository;

    public void getAll(Model model){
        model.addAttribute("list",paymentRepository.findAll());
    }

    public void add(Model model) {
        List<Integer> invoiceId = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            invoiceId.add(order.getInvoice().getId());
        }
        model.addAttribute("invoiceList" ,invoiceId);
    }
    public void  save(Model model, PaymentDTO paymentDTO){
        Invoice invoice = invoiceRepository.findById(paymentDTO.getInvoiceId()).orElseThrow();
        Payment payment = Payment.builder()
                .amount(BigDecimal.valueOf(paymentDTO.getAmount()))
                .invoice(invoice)
                .build();
        paymentRepository.save(payment);
        model.addAttribute("list",paymentRepository.findAll());
    }
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(value = "id") int id, Model model) {
        paymentRepository.deleteById(id);
        return "payment/list";
    }
}
