package com.neting.tiecketapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neting.tiecketapp.Entity.TipoTicket;

@Repository
public interface TipoTicketRepository extends JpaRepository<TipoTicket, Long> {

}
