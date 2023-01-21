package com.springboot.BookYourShow.Controller;

import com.springboot.BookYourShow.RequestDto.BookTicketRequestDto;
import com.springboot.BookYourShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto){

        String message = ticketService.bookTicket(bookTicketRequestDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/cancel-by-id/{id}")
    public ResponseEntity<String> cancelById(@PathVariable("id") int ticketId) {

        ticketService.cancelTicketById(ticketId);
        return new ResponseEntity<>("Ticket cancelled successfully", HttpStatus.ACCEPTED);
    }
}
