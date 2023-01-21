package com.springboot.BookYourShow.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheatreRequestDto {

    private String name;

    private String city;

    private String address;
}
