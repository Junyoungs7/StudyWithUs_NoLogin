package com.jy.swu.privateStudy.controller;

import com.jy.swu.privateStudy.model.PrivateStudy;
import com.jy.swu.privateStudy.service.PrivateStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
public class Controller {

    private final PrivateStudyService studyService;

    //개인 스터디 리스트 보여주기
    @GetMapping("/{id}/list")
    public ResponseEntity<?> getPrivateList(@PathVariable("id")UUID id){
        try{
            List<PrivateStudy> privateStudyList = studyService.findStudyList(id);
            return ResponseEntity.ok().body(privateStudyList);
        }catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
