package uz.pdp.springbootapelsin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTO {
    private Integer customerId; //klient
    private Integer productId; //mahsulot
    private Short quantity; //2ta
}
