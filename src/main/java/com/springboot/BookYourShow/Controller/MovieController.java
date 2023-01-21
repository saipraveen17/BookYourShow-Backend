package com.springboot.BookYourShow.Controller;

import com.springboot.BookYourShow.Models.MovieEntity;
import com.springboot.BookYourShow.RequestDto.MovieRequestDto;
import com.springboot.BookYourShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequestDto movieRequestDto){

        String message = movieService.saveMovie(movieRequestDto);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    //Get movie by Name
    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<MovieEntity> getByName(@PathVariable("name") String movieName) {

        MovieEntity movie = movieService.getMovieByName(movieName);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    //Delete by id
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int movieId) {

        movieService.deleteMovieById(movieId);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    //Delete all
    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAll() {

        movieService.deleteAllMovies();
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

}
