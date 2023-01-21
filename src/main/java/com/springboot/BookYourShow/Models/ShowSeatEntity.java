package com.springboot.BookYourShow.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "show_seats")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNumber;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private boolean isBooked;

    private Date bookingTime;

    @ManyToOne
    @JoinColumn
    private TicketEntity ticket;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

}
