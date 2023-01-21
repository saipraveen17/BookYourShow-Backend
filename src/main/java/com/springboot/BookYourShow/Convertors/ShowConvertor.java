package com.springboot.BookYourShow.Convertors;

import com.springboot.BookYourShow.Models.ShowEntity;
import com.springboot.BookYourShow.RequestDto.ShowRequestDto;
import com.springboot.BookYourShow.ResponseDto.ShowResponseDto;

public class ShowConvertor {

    public static ShowEntity convertDtoToEntity(ShowRequestDto showRequestDto) {

        ShowEntity show = ShowEntity.builder().showDate(showRequestDto.getShowDate())
                .showTime(showRequestDto.getShowTime())
                .multiplier(showRequestDto.getMultiplier()).build();
        return show;
    }

    public static ShowResponseDto convertEntityToDto(ShowEntity show) {

        ShowResponseDto showResponseDto = ShowResponseDto.builder().showDate(show.getShowDate())
                                                                        .showTime(show.getShowTime())
                                                                            .movieName(show.getMovie().getMovieName())
                                                                                .theatreName(show.getTheatre().getName())
                                                                                    .theatreId(show.getTheatre().getId()).build();
        return showResponseDto;
    }
}
