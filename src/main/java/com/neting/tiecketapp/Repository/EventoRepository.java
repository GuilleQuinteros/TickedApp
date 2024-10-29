package com.neting.tiecketapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neting.tiecketapp.Entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

}
