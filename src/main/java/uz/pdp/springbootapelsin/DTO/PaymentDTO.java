package uz.pdp.springbootapelsin.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    int id;
    int invoiceId;
    Double Amount;
}
