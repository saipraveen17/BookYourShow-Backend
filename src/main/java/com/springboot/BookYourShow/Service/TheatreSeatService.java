package com.springboot.BookYourShow.Service;

import com.springboot.BookYourShow.Models.SeatType;
import com.springboot.BookYourShow.Models.TheatreSeatEntity;
import com.springboot.BookYourShow.Repository.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreSeatService {

    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    public List<TheatreSeatEntity> createTheatreSeats(){


        List<TheatreSeatEntity> seats = new ArrayList<>();

        //Naming and cost setting of different types of seats.
        for(int i=1; i<=5; i++) {
            for(int j=0; j<5; j++) {
                String seatNo = ""+i+(char)(j+'A');
                SeatType seatType;
                int rate;
                if(i==1||i==2||i==3) {
                    seatType = SeatType.CLASSIC;
                    rate = 100;
                }
                else {
                    seatType = SeatType.PLATINUM;
                    rate = 200;
                }
                TheatreSeatEntity theaterSeat = new TheatreSeatEntity(seatNo,seatType,rate);
                seats.add(theaterSeat);
            }
        }

        theatreSeatRepository.saveAll(seats);

        return seats;

    }
}
