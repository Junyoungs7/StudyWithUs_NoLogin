package com.jy.swu.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMessageDTO {

    private String message;
    private HttpStatus httpStatus;


    @Builder
    public ResponseMessageDTO(String message, HttpStatus status){
        this.message = message;
        this.httpStatus = status;
    }


}
