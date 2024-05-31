package com.mongoExample.mongo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDto {
    private String id;
    private String name;
    private String surname;
    private String age;
    private String address;
}
