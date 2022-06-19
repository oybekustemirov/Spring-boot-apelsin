package uz.pdp.springbootapelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapelsin.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // nativ query
    // jpa query
}
