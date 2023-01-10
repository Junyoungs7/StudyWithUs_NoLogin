package com.jy.swu.privateStudy.repository;

import com.jy.swu.privateStudy.model.PrivateStudy;
import com.jy.swu.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrivateStudyRepository extends JpaRepository<PrivateStudy, String> {
    Optional<PrivateStudy> findById(String id);
    List<PrivateStudy> findByUser(User user);
}
