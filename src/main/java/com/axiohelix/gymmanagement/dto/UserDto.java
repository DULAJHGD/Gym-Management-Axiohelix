package com.axiohelix.gymmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String userName;

    private String nickname;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    private String phone;

    private String gender;

    private String birthday;

    private Byte[] profilePicture;

}
