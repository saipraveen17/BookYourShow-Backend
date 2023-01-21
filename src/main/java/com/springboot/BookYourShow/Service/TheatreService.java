package com.springboot.BookYourShow.Service;

import com.springboot.BookYourShow.Convertors.TheatreConvertor;
import com.springboot.BookYourShow.Models.MovieEntity;
import com.springboot.BookYourShow.Models.ShowEntity;
import com.springboot.BookYourShow.Models.TheatreEntity;
import com.springboot.BookYourShow.Models.TheatreSeatEntity;
import com.springboot.BookYourShow.Repository.TheatreRepository;
import com.springboot.BookYourShow.RequestDto.TheatreRequestDto;
import com.springboot.BookYourShow.ResponseDto.TheatreResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    TheatreSeatService theatreSeatService;

    @Autowired
    MovieService movieService;

    public String saveTheatre(TheatreRequestDto theatreRequestDto) {

        try
        {
            TheatreEntity theatre = TheatreConvertor.convertDtoToEntity(theatreRequestDto);

            List<TheatreSeatEntity> theaterSeats = theatreSeatService.createTheatreSeats();

            theatre.setTheatreSeats(theaterSeats);

            //For each theater Seat, we need to set the theaterEntity
            for(TheatreSeatEntity theatreSeat : theaterSeats){
                theatreSeat.setTheatre(theatre);
            }

            theatreRepository.save(theatre);
        }
        catch (Exception e) {
            log.info("Save theatre has thrown an error");
            return "Problem in saving the theatre "+e.toString();
        }
        return "Theatre added Successfully";
    }


    public TheatreResponseDto getTheatreById(int theatreId) {

        try {
            return TheatreConvertor.convertEntityToDto(theatreRepository.findById(theatreId).get());
        }
        catch (Exception e) {
            log.info("Get theatre by Id thrown an error "+e.toString());
            return null;
        }
    }

    public List<TheatreResponseDto> getAllTheatres() {

        try {
            List<TheatreResponseDto> theatreResponseDtoList = new ArrayList<>();
            List<TheatreEntity> theatreList = theatreRepository.findAll();
            for(TheatreEntity theatre : theatreList) {
                theatreResponseDtoList.add(TheatreConvertor.convertEntityToDto(theatre));
            }
            return theatreResponseDtoList;
        }
        catch (Exception e) {
            log.info("Get all theatres thrown an error "+e.toString());
            return null;
        }

    }

    public void deleteAllTheatre() {

        theatreRepository.deleteAll();
    }

    public void deleteTheatreById(int theatreId) {

        theatreRepository.deleteById(theatreId);
    }

    public List<String> getTheatresByMovie(String movieName) {

        Set<String> theatresList = new HashSet<>();
        MovieEntity movie = movieService.getMovieByName(movieName);
        List<ShowEntity> showsList = movie.getListOfShows();

        for(ShowEntity show : showsList) {
            String theatreName = show.getTheatre().getName();
            if(!theatresList.contains(theatreName)) {
                theatresList.add(theatreName);
            }
        }
        List<String> listOfTheatres = new ArrayList<>(theatresList);
        return listOfTheatres;
    }
}
