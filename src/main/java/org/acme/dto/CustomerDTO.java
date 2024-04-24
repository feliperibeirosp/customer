package org.acme.dto;


import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class CustomerDTO {

    private String name;

    private String phone;

    private String email;

    private String address;

    private Long age;

}
