package com.springboot.BookYourShow.Service;

import com.springboot.BookYourShow.Convertors.ShowConvertor;
import com.springboot.BookYourShow.Models.MovieEntity;
import com.springboot.BookYourShow.Models.ShowEntity;
import com.springboot.BookYourShow.Models.ShowSeatEntity;
import com.springboot.BookYourShow.Models.TheatreEntity;
import com.springboot.BookYourShow.Repository.MovieRepository;
import com.springboot.BookYourShow.Repository.ShowRepository;
import com.springboot.BookYourShow.Repository.TheatreRepository;
import com.springboot.BookYourShow.RequestDto.ShowRequestDto;
import com.springboot.BookYourShow.ResponseDto.ShowResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class ShowService {
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatService showSeatService;

    public String addShow(ShowRequestDto showRequestDto) {

        try {
            ShowEntity show = ShowConvertor.convertDtoToEntity(showRequestDto);

            String movieName = showRequestDto.getMovieName();
            MovieEntity movie = movieRepository.findByMovieName(movieName);
            show.setMovie(movie);

            int theatreId = showRequestDto.getTheatreId();
            TheatreEntity theatre = theatreRepository.findById(theatreId).get();
            show.setTheatre(theatre);

            List<ShowEntity> movieShowsList = movie.getListOfShows();
            if(movieShowsList==null) {
                movieShowsList = new ArrayList<>();
            }
            movieShowsList.add(show);
            movie.setListOfShows(movieShowsList);

            List<ShowEntity> theatreShowsList = theatre.getShowsList();
            if(theatreShowsList==null) {
                theatreShowsList = new ArrayList<>();
            }
            theatreShowsList.add(show);
            theatre.setShowsList(theatreShowsList);

            List<ShowSeatEntity> showSeatList = showSeatService.createShowSeats(theatre.getTheatreSeats());
            for(ShowSeatEntity showSeat : showSeatList) {
                showSeat.setShow(show);
            }

            theatreRepository.save(theatre);
            movieRepository.save(movie);

        }
        catch (Exception e) {
            log.info("Add Show thrown an Exception");
            return "Problem in adding the Show "+e.toString();
        }
        return "Show added successfully";
    }

    public void deleteShowById(int showId) {

        showRepository.deleteById(showId);
    }

    public void deleteAllShows() {

        showRepository.deleteAll();
    }

    public List<ShowResponseDto> getShowsBetweenGivenPeriod(LocalDateTime fromTime, LocalDateTime toTime) {

        List<ShowEntity> allShows = showRepository.findAll();

        List<ShowResponseDto> showsListBetweenPeriod = new ArrayList<>();

        for(ShowEntity show : allShows) {

            LocalDateTime showDateTime = LocalDateTime.of(show.getShowDate(), show.getShowTime());

            if(showDateTime.compareTo(fromTime)>=0 && showDateTime.compareTo(toTime)<=0) {

                ShowResponseDto showResponseDto = ShowConvertor.convertEntityToDto(show);
                showsListBetweenPeriod.add(showResponseDto);
            }
        }
        return showsListBetweenPeriod;
    }
}
