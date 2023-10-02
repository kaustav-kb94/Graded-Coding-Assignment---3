package com.gl.TicketTrackerApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gl.TicketTrackerApplication.entity.Ticket;

@Repository
@Component
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByTitleContainingIgnoreCaseOrShortDescriptionContainingIgnoreCase(String query, String queryStr);

}
