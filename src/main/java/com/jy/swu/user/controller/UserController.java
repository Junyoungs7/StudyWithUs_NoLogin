package com.jy.swu.user.controller;

import com.jy.swu.user.dto.CreateUserRequestDTO;
import com.jy.swu.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequestDTO requestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("다시 입력해주세요.");
        }
        try{
            String confirm = userService.createUser(requestDTO);
            return ResponseEntity.ok().body("회원가입 완료!"+confirm);
        }catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
