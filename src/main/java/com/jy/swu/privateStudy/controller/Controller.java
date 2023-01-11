package com.jy.swu.privateStudy.controller;

import com.jy.swu.privateStudy.dto.CreateStudyRequestDTO;
import com.jy.swu.privateStudy.model.PrivateStudy;
import com.jy.swu.privateStudy.service.PrivateStudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
@Slf4j
public class Controller {

    private final PrivateStudyService studyService;

    //개인 스터디 리스트 보여주기
    @GetMapping("/{username}/list")
    public ResponseEntity<?> getPrivateList(@PathVariable("username")String userName){
        try{
            List<PrivateStudy> privateStudyList = studyService.findStudyList(userName);
            log.info("controller list: "+privateStudyList.get(0).getName());
            return ResponseEntity.ok().body(privateStudyList);
        }catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createPrivateStudy(@Valid @RequestBody CreateStudyRequestDTO requestDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("다시 입력해주세요.");
        }

        try{
            studyService.createStudy(requestDTO.getName(), requestDTO.getUserName());
            return ResponseEntity.ok().body("개인 스터디가 생성되었습니다.");
        }catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
