package com.springboot.BookYourShow.RequestDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MovieRequestDto {

    private String movieName;

    private int duration;

    private Date releaseDate;
}
