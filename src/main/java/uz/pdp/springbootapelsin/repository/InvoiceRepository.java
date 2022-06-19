package uz.pdp.springbootapelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapelsin.entity.Invoice;
import uz.pdp.springbootapelsin.entity.Product;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    // nativ query
    // jpa query
}
