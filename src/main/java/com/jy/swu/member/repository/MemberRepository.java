package com.jy.swu.member.repository;

import com.jy.swu.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
