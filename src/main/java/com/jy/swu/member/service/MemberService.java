package com.jy.swu.member.service;

import com.jy.swu.member.dto.CreateMemberRequestDTO;
import com.jy.swu.member.dto.MemberResponseDTO;
import com.jy.swu.member.model.Member;
import com.jy.swu.member.model.Role;
import com.jy.swu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void validDuplicated(String userName){
        if(memberRepository.existsByUserName(userName)){
            throw new UsernameNotFoundException(userName + "을 찾을 수 없습니다.");
        }
    }

    // 사용자 생성

    public String createUser(CreateMemberRequestDTO requestDTO){
        validDuplicated(requestDTO.getUserName());
        Member createMember = Member.builder()
                .username(requestDTO.getUserName())
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .role(Role.ROLE_USER)
                .build();
        return memberRepository.save(createMember).getUserName();
    }

    // 사용자 검색
    @Transactional(readOnly = true)
    public MemberResponseDTO findMemberInfoByUserName(String userName){
        return memberRepository.findByUserName(userName).map(MemberResponseDTO::of).orElseThrow(()->new RuntimeException("유저 정보가 없습니다."));
    }
}
