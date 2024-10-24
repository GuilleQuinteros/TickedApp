package com.neting.tiecketapp.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.neting.tiecketapp.Enum.EstadoCompra;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private BigDecimal total;
    private String metodopago;

    @Enumerated(EnumType.STRING)
    private EstadoCompra estado;


    private String codigoTransaccion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @OneToMany(mappedBy = "compra")
    private List<Ticket> tickets;

    public void generarCodigoTransaccion(){
        this.codigoTransaccion = UUID.randomUUID().toString();
    }
}
