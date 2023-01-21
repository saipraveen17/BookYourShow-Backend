package com.springboot.BookYourShow.Service;

import com.springboot.BookYourShow.Convertors.TicketConvertor;
import com.springboot.BookYourShow.Convertors.UserConvertor;
import com.springboot.BookYourShow.Models.TicketEntity;
import com.springboot.BookYourShow.Models.UserEntity;
import com.springboot.BookYourShow.Repository.TicketRepository;
import com.springboot.BookYourShow.Repository.UserRepository;
import com.springboot.BookYourShow.RequestDto.UserRequestDto;
import com.springboot.BookYourShow.ResponseDto.TicketResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String createUser(UserRequestDto userRequestDto){

        //Converted the userRequestDto to UserEntity
        try
        {
            UserEntity user = UserConvertor.convertDtoToEntity(userRequestDto);
            userRepository.save(user);
        }
        catch (Exception e) {
            log.info("Create User has thrown an error");
            return "Problem in crating the user "+e.toString();
        }
        return "User added successfully";
    }

    public UserEntity findUserByName(String name) {

        return userRepository.findByName(name);
    }

    public List<UserEntity> findAllUsers() {

        return userRepository.findAll();
    }

    public List<TicketResponseDto> getTicketsBookedByUser(int userId) {

        List<TicketResponseDto> ticketsList = new ArrayList<>();
        List<TicketEntity> allTickets = ticketRepository.findAll();

        for(TicketEntity ticket : allTickets) {

            if(ticket.getUser().getId()==userId) {
                ticketsList.add(TicketConvertor.convertEntityToDto(ticket));
            }
        }
        return ticketsList;
    }
}
