package com.jy.swu.privateStudy.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateStudyRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private String userName;

    @Builder
    public CreateStudyRequestDTO(String name, String userName){
        this.name = name;
        this.userName = userName;
    }

}
