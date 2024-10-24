package com.neting.tiecketapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoQr;
    private boolean usado;

    @ManyToOne
    @JoinColumn(name ="tipo_ticket_id")
    private TipoTicket tipo_ticked;

    @ManyToOne
    @JoinColumn(name ="usuario_id")
    private User usuario;

    
}
