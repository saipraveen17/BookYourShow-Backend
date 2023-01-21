package com.springboot.BookYourShow.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {

    private int ticketId;

    private String allottedSeats;

    private int amount;

    private String movieName;

    private String theatreName;

    private LocalDate showDate;

    private LocalTime showTime;
}
