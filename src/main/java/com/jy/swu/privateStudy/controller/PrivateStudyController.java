package com.jy.swu.privateStudy.controller;

import com.jy.swu.privateStudy.dto.CreateStudyRequestDTO;
import com.jy.swu.privateStudy.dto.CreateTodoRequestDTO;
import com.jy.swu.privateStudy.model.PrivateStudy;
import com.jy.swu.privateStudy.service.PrivateStudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
@Slf4j
public class PrivateStudyController {

    private final PrivateStudyService studyService;

    //개인 스터디 리스트 보여주기
    @GetMapping("/{username}")
    public ResponseEntity<?> getPrivateList(@PathVariable("username")String userName){
        try{
            List<PrivateStudy> privateStudyList = studyService.findStudyList(userName);
            return ResponseEntity.ok().body(privateStudyList);
        }catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 개인 스터디 생성
    @PostMapping("/create")
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

//    todo 생성
    @PostMapping("/{userName}/{studyName}")
    public ResponseEntity<?> createTodo(@PathVariable("userName") String userName,
                                        @PathVariable("studyName") String studyName,
                                        @Valid @RequestBody CreateTodoRequestDTO requestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }
        try{
            studyService.createTodo(requestDTO.getName(), studyName);
            return ResponseEntity.ok().body("Todo 생성!");
        }catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
