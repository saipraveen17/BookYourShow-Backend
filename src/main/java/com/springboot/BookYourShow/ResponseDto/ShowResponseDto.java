package com.springboot.BookYourShow.ResponseDto;

import com.springboot.BookYourShow.Models.MovieEntity;
import com.springboot.BookYourShow.Models.TheatreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowResponseDto {

    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;

    private String theatreName;

    private int theatreId;
}
