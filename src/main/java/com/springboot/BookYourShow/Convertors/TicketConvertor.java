package com.springboot.BookYourShow.Convertors;

import com.springboot.BookYourShow.Models.TicketEntity;
import com.springboot.BookYourShow.ResponseDto.TicketResponseDto;

public class TicketConvertor {

    public static TicketResponseDto convertEntityToDto(TicketEntity ticket) {

        TicketResponseDto ticketResponseDto = TicketResponseDto.builder().allottedSeats(ticket.getAllottedSeats())
                                                                            .amount(ticket.getAmount())
                                                                                .movieName(ticket.getShow().getMovie().getMovieName())
                                                                                    .theatreName(ticket.getShow().getTheatre().getName())
                                                                                        .ticketId(ticket.getId())
                                                                                            .showDate(ticket.getShow().getShowDate())
                                                                                                .showTime(ticket.getShow().getShowTime()).build();
        return ticketResponseDto;
    }
}
