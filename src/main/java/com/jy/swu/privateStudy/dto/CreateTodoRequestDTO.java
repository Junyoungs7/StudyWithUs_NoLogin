package com.jy.swu.privateStudy.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTodoRequestDTO {
    @NotNull
    private String name;

    @Builder
    public CreateTodoRequestDTO(String name){
        this.name = name;
    }
}
