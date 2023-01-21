package com.springboot.BookYourShow.Controller;

import com.springboot.BookYourShow.Models.TheatreEntity;
import com.springboot.BookYourShow.RequestDto.TheatreRequestDto;
import com.springboot.BookYourShow.ResponseDto.TheatreResponseDto;
import com.springboot.BookYourShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {


    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheatre(@RequestBody() TheatreRequestDto theatreRequestDto) {

        String message = theatreService.saveTheatre(theatreRequestDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-movie/{name}")
    public ResponseEntity<List<String>> getByMovie(@PathVariable("name") String movieName) {

        List<String> theatresList = theatreService.getTheatresByMovie(movieName);
        return new ResponseEntity<>(theatresList, HttpStatus.OK);
    }

    //Get theaters by theaterId
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<TheatreResponseDto> getById(@PathVariable("id") int theatreId) {
        TheatreResponseDto theatreResponseDto = theatreService.getTheatreById(theatreId);
        return new ResponseEntity<>(theatreResponseDto, HttpStatus.OK);
    }

    //Get all theaters
    @GetMapping("/get-all")
    public ResponseEntity<List<TheatreResponseDto>> getAll() {
        List<TheatreResponseDto> theatreResponseDtoListList = theatreService.getAllTheatres();
        return new ResponseEntity<>(theatreResponseDtoListList, HttpStatus.OK);
    }

    //Delete theatre by Id
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int theatreId) {
        theatreService.deleteTheatreById(theatreId);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.ACCEPTED);
    }

    //Delete all theatres
    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAll() {
        theatreService.deleteAllTheatre();
        return new ResponseEntity<>("Deleted successfully", HttpStatus.ACCEPTED);
    }
}
