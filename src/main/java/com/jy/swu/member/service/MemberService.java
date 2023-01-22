package com.jy.swu.member.service;

import com.jy.swu.jwt.TokenProvider;
import com.jy.swu.jwt.dto.TokenDTO;
import com.jy.swu.jwt.dto.TokenRequestDTO;
import com.jy.swu.jwt.model.RefreshToken;
import com.jy.swu.jwt.repository.RefreshTokenRepository;
import com.jy.swu.member.dto.CreateMemberRequestDTO;
import com.jy.swu.member.dto.LoginRequestDTO;
import com.jy.swu.member.dto.MemberResponseDTO;
import com.jy.swu.member.model.Member;
import com.jy.swu.member.model.Role;
import com.jy.swu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public void validDuplicated(String userName){
        if(memberRepository.existsByUserName(userName)){
            throw new UsernameNotFoundException(userName + "을 찾을 수 없습니다.");
        }
    }

    // 사용자 생성
    @Transactional
    public Member createUser(CreateMemberRequestDTO requestDTO){
        validDuplicated(requestDTO.getUserName());
        Member createMember = Member.builder()
                .username(requestDTO.getUserName())
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        return memberRepository.save(createMember);
    }

    // 로그인
    @Transactional
    public TokenDTO login(LoginRequestDTO loginRequestDTO){
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = loginRequestDTO.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDTO.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDTO;
    }

    @Transactional
    public TokenDTO reissue(TokenRequestDTO tokenRequestDTO){
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDTO.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDTO.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDTO.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDTO.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDTO;
    }

    // 사용자 검색
    @Transactional(readOnly = true)
    public MemberResponseDTO findMemberInfoByUserName(String userName){
        return memberRepository.findByUserName(userName).map(MemberResponseDTO::of).orElseThrow(()->new RuntimeException("유저 정보가 없습니다."));
    }
}
