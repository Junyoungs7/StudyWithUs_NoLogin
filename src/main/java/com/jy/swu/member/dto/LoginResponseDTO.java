package com.jy.swu.member.dto;

import com.jy.swu.jwt.dto.TokenDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDTO {

    private HttpStatus status;
    private TokenDTO tokenDTO;


    @Builder
    public LoginResponseDTO(HttpStatus status, TokenDTO tokenDTO){
        this.status = status;
        this.tokenDTO = tokenDTO;
    }
}
