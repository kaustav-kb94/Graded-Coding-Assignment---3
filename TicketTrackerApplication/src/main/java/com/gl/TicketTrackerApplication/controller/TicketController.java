package com.gl.TicketTrackerApplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.TicketTrackerApplication.entity.Ticket;
import com.gl.TicketTrackerApplication.service.TicketService;


@Controller
@RequestMapping("/admin")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@GetMapping("/tickets")
	public ModelAndView getTickets(Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("tickets");
		try {
			List<Ticket> ticketList = ticketService.getAllTickets();
			model.addAttribute("tickets", ticketList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mv;		
	}

	@GetMapping("/tickets/newTicket")
	public ModelAndView createNewTickets(Model model) {
		model.addAttribute("ticket", new Ticket());
		ModelAndView mv = new ModelAndView("create_ticket");	
		return mv;		
	}

	@PostMapping("/tickets")
	public ModelAndView saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		try {
			ticketService.saveTicket(ticket);
		}catch(Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("redirect:/admin/tickets");	
		return mv;		
	}

	@GetMapping("/tickets/{id}/edit")
	public String showEditTicketForm(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("ticket", ticketService.getTicketById(id));			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "edit_ticket";

	}

	@PostMapping("/tickets/{id}")
	public ModelAndView editTicket(@PathVariable Long id, @ModelAttribute("ticket") Ticket ticket, Model model) {
		try {
			Ticket dbTicket = ticketService.getTicketById(id);		
			ticketService.updateTicket(ticket, dbTicket);			
		}catch(Exception e) {
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("redirect:/admin/tickets");	
		return mv;
	}

	@GetMapping("/tickets/{id}/delete")
	public String deleteTicket(@PathVariable Long id, Model model) {
		try {
			ticketService.deleteTicketById(id);			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/admin/tickets";

	}

	@GetMapping("/tickets/{id}/view")
	public String viewTicketForm(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("ticket", ticketService.getTicketById(id));			
		}catch(Exception e) {
			e.printStackTrace();
		}

		return "view_ticket";

	}

	@PostMapping("/tickets/search")
	public ModelAndView searchTicket(@RequestParam("query") String query, Model model) {
		ModelAndView mv = new ModelAndView("redirect:/admin/tickets/search?query="+query);	
		return mv;
	}

	@GetMapping("/tickets/search")
	public ModelAndView searchFilterTicket(@RequestParam("query") String queryStr, Model model) {
		ModelAndView mv = new ModelAndView("tickets");
		try {
			List<Ticket> ticketList = ticketService.getTicketsBySearchCriteria(queryStr, queryStr);
			model.addAttribute("tickets", ticketList);
			model.addAttribute("queryString", queryStr);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return mv;	
	}

}
