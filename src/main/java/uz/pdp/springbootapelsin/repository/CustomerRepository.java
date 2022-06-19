package uz.pdp.springbootapelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapelsin.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // nativ query
    // jpa query
}
