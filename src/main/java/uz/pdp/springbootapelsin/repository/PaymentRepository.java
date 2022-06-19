package uz.pdp.springbootapelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapelsin.entity.Order;
import uz.pdp.springbootapelsin.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // nativ query
    // jpa query
}
