package com.mongoExample.mongo.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDto {
    private String dni;
    private String name;
    private String surname;
    private String age;
    private String address;
}
