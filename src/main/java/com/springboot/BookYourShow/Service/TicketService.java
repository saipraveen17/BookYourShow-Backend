package com.springboot.BookYourShow.Service;

import com.springboot.BookYourShow.Models.ShowEntity;
import com.springboot.BookYourShow.Models.ShowSeatEntity;
import com.springboot.BookYourShow.Models.TicketEntity;
import com.springboot.BookYourShow.Models.UserEntity;
import com.springboot.BookYourShow.Repository.ShowRepository;
import com.springboot.BookYourShow.Repository.ShowSeatRepository;
import com.springboot.BookYourShow.Repository.TicketRepository;
import com.springboot.BookYourShow.Repository.UserRepository;
import com.springboot.BookYourShow.RequestDto.BookTicketRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TicketService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) {

        try {

            //Retrieve data from Dto
            List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();
            ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();
            UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

            //Check seats availability
            List<ShowSeatEntity> showSeats = showEntity.getSeatsList();
            List<ShowSeatEntity> bookedSeats = new ArrayList<>();

            for(ShowSeatEntity showSeat : showSeats){
                String seatNo = showSeat.getSeatNumber();
                if(showSeat.isBooked()==false&&requestedSeats.contains(seatNo)){
                    bookedSeats.add(showSeat);
                }
            }

            //If not throw exception
            if(bookedSeats.size()!=requestedSeats.size()){
                throw new Exception("Requested seats are not available");
            }

            //Else book ticket
            TicketEntity ticketEntity = new TicketEntity();

            double totalAmount = 0;
            double multiplier = showEntity.getMultiplier();
            String allotedSeats  = "";
            int rate = 0;

            //Bi-directional
            /*List<ShowSeatEntity> showSeatsList = showEntity.getSeatsList();
            if(showSeatsList==null) {
                showSeatsList = new ArrayList<>();
            }*/

            for(ShowSeatEntity bookedSeat: bookedSeats){

                bookedSeat.setBooked(true);
                bookedSeat.setBookingTime(new Date());
                bookedSeat.setTicket(ticketEntity);

                String seatNo = bookedSeat.getSeatNumber();

                allotedSeats = allotedSeats + seatNo + ",";

                if(seatNo.charAt(0)=='4'||seatNo.charAt(0)=='5') {
                    rate = 200;
                }
                else {
                    rate = 100;
                }
                totalAmount = totalAmount + multiplier*rate;
                //showSeatsList.add(bookedSeat);

            }
            allotedSeats = allotedSeats.substring(0,allotedSeats.length()-1);

            ticketEntity.setBookedAt(new Date());
            ticketEntity.setAmount((int)totalAmount);
            ticketEntity.setShow(showEntity);
            ticketEntity.setUser(userEntity);
            ticketEntity.setSeatsBooked(bookedSeats);
            ticketEntity.setAllottedSeats(allotedSeats);

            //Bi-directional
            /*List<TicketEntity> ticketList = userEntity.getTicketsBooked();
            if(ticketList==null) {
                ticketList = new ArrayList<>();
            }
            ticketList.add(ticketEntity);
            userEntity.setTicketsBooked(ticketList);

            List<TicketEntity> showTicketsList = showEntity.getTicketsBookedForShow();
            if(showTicketsList==null) {
                showTicketsList = new ArrayList<>();
            }
            showTicketsList.add(ticketEntity);
            showEntity.setTicketsBookedForShow(showTicketsList);*/

            //save ticket
            //userRepository.save(userEntity);
            //showRepository.save(showEntity);
            ticketRepository.save(ticketEntity);

            return "Successfully created a ticket";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public void cancelTicketById(int ticketId) {

        TicketEntity ticket = ticketRepository.findById(ticketId).get();
        ShowEntity show = ticket.getShow();
        UserEntity user = ticket.getUser();
        List<ShowSeatEntity> showSeatsList = ticket.getSeatsBooked();

        for (ShowSeatEntity showSeat : showSeatsList) {
            showSeat.setBooked(false);
            showSeat.setBookingTime(null);
            showSeat.setTicket(null);
        }
        showSeatRepository.saveAll(showSeatsList);
        ticket.setSeatsBooked(null);
        ticketRepository.delete(ticket);
    }
}
