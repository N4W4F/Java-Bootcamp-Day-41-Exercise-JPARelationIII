package com.example.schoolsystem.OutDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
    private String area;
    private String street;
    private Integer buildingNumber;
}
