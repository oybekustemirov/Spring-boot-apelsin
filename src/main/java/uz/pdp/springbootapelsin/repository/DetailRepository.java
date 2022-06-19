package uz.pdp.springbootapelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapelsin.entity.Customer;
import uz.pdp.springbootapelsin.entity.Detail;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
    // nativ query
    // jpa query
}
