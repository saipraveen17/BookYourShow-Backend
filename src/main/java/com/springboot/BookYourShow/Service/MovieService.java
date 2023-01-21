package com.springboot.BookYourShow.Service;

import com.springboot.BookYourShow.Models.MovieEntity;
import com.springboot.BookYourShow.Convertors.MovieConvertor;
import com.springboot.BookYourShow.Models.MovieEntity;
import com.springboot.BookYourShow.Repository.MovieRepository;
import com.springboot.BookYourShow.RequestDto.MovieRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String saveMovie(MovieRequestDto movieRequestDto){

        //Conversion of Dto to Entity is required for saving it to the Database.
        try
        {
            MovieEntity movie = MovieConvertor.convertDtoToEntity(movieRequestDto);
            movieRepository.save(movie);
        }
        catch (Exception e) {
            log.info("Save Movie has thrown an error");
            return "Problem in saving the Movie "+e.toString();
        }
        return "Movie added successfully";
    }

    public MovieEntity getMovieByName(String movieName) {

        return movieRepository.findByMovieName(movieName);
    }

    public void deleteMovieById(int movieId) {

        movieRepository.deleteById(movieId);
    }

    public void deleteAllMovies() {

        movieRepository.deleteAll();
    }
}
