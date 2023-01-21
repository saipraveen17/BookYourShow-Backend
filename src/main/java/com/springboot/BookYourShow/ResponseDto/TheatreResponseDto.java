package com.springboot.BookYourShow.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheatreResponseDto {

    private int id;

    private String name;

    private String city;

    private String address;
}
