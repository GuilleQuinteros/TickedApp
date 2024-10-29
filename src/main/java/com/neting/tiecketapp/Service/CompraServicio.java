package com.neting.tiecketapp.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadopago.client.MercadoPagoClient;
import com.mercadopago.resources.payment.Payment;
import com.neting.tiecketapp.Entity.Compra;
import com.neting.tiecketapp.Entity.Ticket;
import com.neting.tiecketapp.Entity.User;
import com.neting.tiecketapp.Enum.EstadoCompra;
import com.neting.tiecketapp.Repository.CompraRepository;
import com.neting.tiecketapp.Repository.UserRepository;

@Service
public class CompraServicio {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketService ticketService;

    public Compra nuevaCompra(Long userId, List<Ticket> tickets,BigDecimal total, String metodopago  ){
        User usuario= userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado")) ;

        Compra compra = new Compra();

        compra.setFecha(LocalDateTime.now());
        compra.setUsuario(usuario);
        compra.setTickets(tickets);
        compra.setTotal(total);
        compra.setMetodopago(metodopago);
        compra.setEstado(EstadoCompra.PENDIENTE);

        return compraRepository.save(compra);

        // Verificar estado de pago con Mercado Pago
        String mercadoPagoStatus = verificarPagoConMercadoPago(transactionId);

        // Actualizar el estado según la respuesta de Mercado Pago
        if ("approved".equals(mercadoPagoStatus)) {
            compra.setEstado(EstadoCompra.PAGADA);
        } else {
            System.out.println("El pago no fue aprobado: " + mercadoPagoStatus);
        }
        
        // Guardar los cambios en la base de datos y retornar la compra
        return compraRepository.save(compra);
    }

    // Método simulado para verificar el estado del pago en Mercado Pago
    private String verificarPagoConMercadoPago(String transactionId) {
        // Simulación de llamada a la API de Mercado Pago usando el transactionId para obtener el estado del pago

        try {
            MercadoPagoClient.SDK.setAccessToken("TU_ACCESS_TOKEN");
            Payment payment = Payment.findById(transactionId);
            return payment.getStatus(); // Retorna "approved", "pending", "rejected", etc.
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
    public Compra procesarDevolucion(String codigoTransaccion){
        
        Compra compra = compraRepository.findByCodigoTransaccion(codigoTransaccion).orElseThrow(()-> new RuntimeException("Compra no Encontrada"));


        if (compra.getEstado() != EstadoCompra.DEVUELTA) {
            compra.setEstado(EstadoCompra.DEVUELTA);
            return compraRepository.save(compra);
        }else{
        throw new RuntimeException("Esta compra ya fue devuelta");
        }

        
}


    public Compra buscarCompraPorTransaccion(String codigoTransaccion){
        return compraRepository.findByCodigoTransaccion(codigoTransaccion).orElseThrow(() -> new RuntimeException("Compra No Encontrada"));
    }



}