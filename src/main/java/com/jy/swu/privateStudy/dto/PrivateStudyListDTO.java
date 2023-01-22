package com.jy.swu.privateStudy.dto;

import com.jy.swu.privateStudy.model.PrivateStudy;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrivateStudyListDTO {

    private List<PrivateStudy> privateStudyList;

    @Builder
    public PrivateStudyListDTO(List<PrivateStudy> privateStudyList){
        this.privateStudyList = privateStudyList;
    }
}
