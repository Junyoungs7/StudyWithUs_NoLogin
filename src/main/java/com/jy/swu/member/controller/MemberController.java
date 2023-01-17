package com.jy.swu.member.controller;

import com.jy.swu.member.dto.CreateMemberRequestDTO;
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
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateMemberRequestDTO requestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("다시 입력해주세요.");
        }
        try{
            String confirm = memberService.createUser(requestDTO);
            return ResponseEntity.ok().body("회원가입 완료!"+confirm);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<MemberResponseDTO> findMemberInfoByUserName(@PathVariable("username") String userName){
        return ResponseEntity.ok(memberService.findMemberInfoByUserName(userName));
    }

}
