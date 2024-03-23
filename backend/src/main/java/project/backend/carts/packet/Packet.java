package project.backend.carts.packet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Packet {
    @SequenceGenerator(
            name = "packet_sequence",
            sequenceName = "packet_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "packet_sequence"
    )
    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean isActive;
}
