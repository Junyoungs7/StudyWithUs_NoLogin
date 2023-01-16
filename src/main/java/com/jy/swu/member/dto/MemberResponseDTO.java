package com.jy.swu.member.dto;

import com.jy.swu.member.model.Member;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDTO {

    private String userName;

    private String name;

    private String email;

    public static MemberResponseDTO of(Member member){
        return MemberResponseDTO.builder()
                .email(member.getEmail())
                .userName(member.getUserName())
                .name(member.getName())
                .build();
    }


}
