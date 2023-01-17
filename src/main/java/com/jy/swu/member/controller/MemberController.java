package com.jy.swu.member.controller;

import com.jy.swu.jwt.dto.TokenDTO;
import com.jy.swu.jwt.dto.TokenRequestDTO;
import com.jy.swu.member.dto.CreateMemberRequestDTO;
import com.jy.swu.member.dto.LoginRequestDTO;
import com.jy.swu.member.dto.MemberResponseDTO;
import com.jy.swu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            return ResponseEntity.ok().body(memberService.createUser(requestDTO));
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
            return ResponseEntity.ok(memberService.login(requestDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenRequestDTO tokenRequestDTO){
        return ResponseEntity.ok(memberService.reissue(tokenRequestDTO));
    }

    @GetMapping("/{username}")
    public ResponseEntity<MemberResponseDTO> findMemberInfoByUserName(@PathVariable("username") String userName) {
        return ResponseEntity.ok(memberService.findMemberInfoByUserName(userName));
    }

}
