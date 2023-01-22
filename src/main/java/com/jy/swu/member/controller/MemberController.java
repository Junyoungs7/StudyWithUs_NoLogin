package com.jy.swu.member.controller;

import com.jy.swu.jwt.dto.TokenDTO;
import com.jy.swu.jwt.dto.TokenRequestDTO;
import com.jy.swu.member.dto.*;
import com.jy.swu.member.model.Member;
import com.jy.swu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateMemberRequestDTO requestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("다시 입력해주세요.");
        }
        try{
            memberService.createUser(requestDTO);
            ResponseMessageDTO messageDTO = ResponseMessageDTO.builder()
                    .message("회원가입 성공")
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(messageDTO);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO requestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("로그인 요청 실패");
        }
        try {
            TokenDTO responseToken = memberService.login(requestDTO);
            return ResponseEntity.ok(new LoginResponseDTO(HttpStatus.OK, responseToken));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenRequestDTO tokenRequestDTO){
        return ResponseEntity.ok(memberService.reissue(tokenRequestDTO));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findMemberInfoByUserName(@PathVariable("username") String userName) {
        try{
            return ResponseEntity.ok(memberService.findMemberInfoByUserName(userName));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
