package com.neting.tiecketapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neting.tiecketapp.Entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
