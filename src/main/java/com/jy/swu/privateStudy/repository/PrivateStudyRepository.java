package com.jy.swu.privateStudy.repository;

import com.jy.swu.privateStudy.model.PrivateStudy;
import com.jy.swu.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrivateStudyRepository extends JpaRepository<PrivateStudy, UUID> {
    Optional<PrivateStudy> findById(UUID id);
    List<PrivateStudy> findByUser(User user);

    PrivateStudy findByName(String name);
}
