package com.jy.swu.member.repository;

import com.jy.swu.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByUserName(String userName);

    boolean existsByUserName(String userName);
}
