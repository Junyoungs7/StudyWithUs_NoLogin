package com.jy.swu.privateStudy.repository;

import com.jy.swu.privateStudy.model.PrivateStudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivateStudyRepository extends JpaRepository<PrivateStudy, String> {
    Optional<PrivateStudy> findById(String id);
}
