package com.springboot.BookYourShow.Convertors;

import com.springboot.BookYourShow.Models.MovieEntity;
import com.springboot.BookYourShow.RequestDto.MovieRequestDto;

import java.util.Date;

public class MovieConvertor {

    public static MovieEntity convertDtoToEntity(MovieRequestDto movieRequestDto) {

        MovieEntity movie = MovieEntity.builder().movieName(movieRequestDto.getMovieName())
                .duration(movieRequestDto.getDuration())
                .releaseDate((Date) movieRequestDto.getReleaseDate()).build();
        return movie;
    }
}
