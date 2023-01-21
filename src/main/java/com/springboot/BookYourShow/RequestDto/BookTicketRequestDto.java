package com.springboot.BookYourShow.RequestDto;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequestDto {

    private List<String> requestSeats;

    private int showId;

    private int userId;
}
