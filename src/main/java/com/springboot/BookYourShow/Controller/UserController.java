package com.springboot.BookYourShow.Controller;

import com.springboot.BookYourShow.Models.UserEntity;
import com.springboot.BookYourShow.RequestDto.UserRequestDto;
import com.springboot.BookYourShow.ResponseDto.TicketResponseDto;
import com.springboot.BookYourShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody() UserRequestDto userRequestDto) {

        String message = userService.createUser(userRequestDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<UserEntity> getByUser(@PathVariable("name") String name) {

        UserEntity user = userService.findUserByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get-all-tickets/{id}")
    public ResponseEntity<List<TicketResponseDto>> getAllTickets(@PathVariable("id") int userId) {

        List<TicketResponseDto> ticketsList = userService.getTicketsBookedByUser(userId);
        return new ResponseEntity<>(ticketsList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserEntity>> getAllUsers() {

        List<UserEntity> userList = userService.findAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}