package com.springboot.BookYourShow.Convertors;

import com.springboot.BookYourShow.Models.UserEntity;
import com.springboot.BookYourShow.RequestDto.UserRequestDto;

public class UserConvertor {

    public static UserEntity convertDtoToEntity(UserRequestDto userRequestDto) {

        UserEntity user = UserEntity.builder().name(userRequestDto.getName())
                .mobile(userRequestDto.getMobile()).build();
        return user;
    }
}
