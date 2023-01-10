package com.jy.swu.privateStudy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
public class Controller {

    //개인 스터디 리스트 보여주기
    @GetMapping("/{id}/list")
    public ResponseEntity<?> getPrivateList(@PathVariable("id")UUID id){
        try{
            return null;
        }catch (IllegalStateException e){
            return null;
        }
    }
}
