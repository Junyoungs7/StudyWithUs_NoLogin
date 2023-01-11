package com.jy.swu.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserRequestDTO {

    @NotNull
    private String userName;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @Builder
    public CreateUserRequestDTO(String userName, String email, String name, String password){
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
