package com.gl.TicketTrackerApplication.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.TicketTrackerApplication.entity.Ticket;
import com.gl.TicketTrackerApplication.repository.TicketRepository;
import com.gl.TicketTrackerApplication.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public void saveTicket(Ticket ticket) {
		/* Adding todays date in the created on field */
		ticket.setCreatedOn(new Date());
		ticketRepository.save(ticket);		
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getTicketById(Long id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		if(ticket.isPresent()) {
			return ticket.get();
		}else {
			throw new RuntimeException ("Ticket Does not exist");
		}
	}

	@Override
	public Ticket updateTicket(Ticket ticket, Ticket dbTicket) {
		dbTicket.setTitle(ticket.getTitle());
		dbTicket.setShortDescription(ticket.getShortDescription());
		dbTicket.setContent(ticket.getContent());
		return ticketRepository.save(dbTicket);
	}

	@Override
	public void deleteTicketById(Long id) {
		ticketRepository.deleteById(id);
	}

	@Override
	public List<Ticket> getTicketsBySearchCriteria(String query, String queryStr) {
		return ticketRepository.findByTitleContainingIgnoreCaseOrShortDescriptionContainingIgnoreCase(query,queryStr);
	}

}
