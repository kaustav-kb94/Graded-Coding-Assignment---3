package com.gl.TicketTrackerApplication.service;


import java.util.List;

import com.gl.TicketTrackerApplication.entity.Ticket;

public interface TicketService {

	void saveTicket(Ticket ticket);

	List<Ticket> getAllTickets();

	Ticket getTicketById(Long id);

	Ticket updateTicket(Ticket ticket, Ticket dbTicket);

	void deleteTicketById(Long id);

	List<Ticket> getTicketsBySearchCriteria(String query, String queryStr);

}
