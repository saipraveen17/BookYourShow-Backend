package com.springboot.BookYourShow.Service;

import com.springboot.BookYourShow.Models.ShowSeatEntity;
import com.springboot.BookYourShow.Models.TheatreSeatEntity;
import com.springboot.BookYourShow.Repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowSeatService {

    @Autowired
    ShowSeatRepository showSeatRepository;

    public List<ShowSeatEntity> createShowSeats(List<TheatreSeatEntity> theatreSeatList) {

        List<ShowSeatEntity> showSeatList = new ArrayList<>();

        for(TheatreSeatEntity theatreSeat : theatreSeatList) {
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNumber(theatreSeat.getSeatNumber())
                    .seatType(theatreSeat.getSeatType()).build();
            showSeatList.add(showSeat);
        }

        showSeatRepository.saveAll(showSeatList);
        return showSeatList;
    }
}
