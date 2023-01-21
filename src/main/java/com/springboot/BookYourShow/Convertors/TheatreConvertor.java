package com.springboot.BookYourShow.Convertors;

import com.springboot.BookYourShow.Models.TheatreEntity;
import com.springboot.BookYourShow.RequestDto.TheatreRequestDto;
import com.springboot.BookYourShow.ResponseDto.TheatreResponseDto;

public class TheatreConvertor {

    public static TheatreEntity convertDtoToEntity(TheatreRequestDto theatreRequestDto) {

        TheatreEntity theatre = TheatreEntity.builder().name(theatreRequestDto.getName())
                .city(theatreRequestDto.getCity())
                .address(theatreRequestDto.getAddress()).build();
        return theatre;
    }

    public static TheatreResponseDto convertEntityToDto(TheatreEntity theatreEntity) {

        TheatreResponseDto theatreResponseDto = TheatreResponseDto.builder().id(theatreEntity.getId())
                                                                                .name(theatreEntity.getName())
                                                                                    .city(theatreEntity.getCity())
                                                                                        .address(theatreEntity.getAddress()).build();
        return theatreResponseDto;
    }
}
