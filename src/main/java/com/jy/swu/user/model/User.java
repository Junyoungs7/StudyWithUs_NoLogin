package com.jy.swu.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jy.swu.privateStudy.model.PrivateStudy;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<PrivateStudy> privateStudyList = new ArrayList<>();

    @Builder
    public User(String username, String name, String email, String password){
        this.email = email;
        this.name = name;
        this.userName = username;
        this.password = password;
    }


}
