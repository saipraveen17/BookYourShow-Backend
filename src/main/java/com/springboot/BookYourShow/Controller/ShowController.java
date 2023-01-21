package com.springboot.BookYourShow.Controller;

import com.springboot.BookYourShow.RequestDto.ShowRequestDto;
import com.springboot.BookYourShow.ResponseDto.ShowResponseDto;
import com.springboot.BookYourShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowRequestDto showRequestDto){

        String message = showService.addShow(showRequestDto);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-shows-between-certain-period/{from}/{to}")
    public ResponseEntity<List<ShowResponseDto>> getShowsBetweenPeriod(@PathVariable("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromTime, @PathVariable("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toTime) {

        List<ShowResponseDto> showsList = showService.getShowsBetweenGivenPeriod(fromTime, toTime);
        return new ResponseEntity<>(showsList, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int showId) {

        showService.deleteShowById(showId);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAll() {

        showService.deleteAllShows();
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

}