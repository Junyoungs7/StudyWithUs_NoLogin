package com.jy.swu.privateStudy.service;

import com.jy.swu.privateStudy.model.PrivateStudy;
import com.jy.swu.privateStudy.repository.PrivateStudyRepository;
import com.jy.swu.user.model.User;
import com.jy.swu.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrivateStudyService {

    private final PrivateStudyRepository studyRepository;
    private final UserRepository userRepository;

    public User findUser(UUID id){
        User findUser = userRepository.findById(id).get();
        if (findUser == null){
            throw new IllegalStateException("사용자가 없습니다.");
        }else{
            return findUser;
        }
    }

    public List<PrivateStudy> findStudyList(UUID id){
        User findUser = findUser(id);
        return studyRepository.findByUser(findUser);
    }
}
