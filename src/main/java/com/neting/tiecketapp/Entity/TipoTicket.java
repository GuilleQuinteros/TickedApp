package com.neting.tiecketapp.Entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
public class TipoTicket {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private BigDecimal precio;
    private int cantidadDisponible;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @OneToMany (mappedBy = "tipoTicked")
    private List<TipoTicket> tickets;
}
