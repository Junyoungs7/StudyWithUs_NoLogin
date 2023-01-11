package com.jy.swu.user.service;

import com.jy.swu.user.dto.CreateUserRequestDTO;
import com.jy.swu.user.model.User;
import com.jy.swu.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void validDuplicated(String userName){
        User validUser = userRepository.findByUserName(userName);
        if(validUser != null){
            throw new IllegalStateException("이미 존재하는 사용자입니다.");
        }

    }


    public String createUser(CreateUserRequestDTO requestDTO){
        validDuplicated(requestDTO.getUserName());
        User createUser = User.builder()
                .username(requestDTO.getUserName())
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .build();
        return userRepository.save(createUser).getUserName();
    }
}
