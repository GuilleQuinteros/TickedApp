package com.neting.tiecketapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.neting.tiecketapp.Entity.Compra;
@Repository
public interface CompraRepository extends JpaRepository <Compra, Long> {

    Optional<Compra> findByCodigoTransaccion(String codigoTransaccion);
}
