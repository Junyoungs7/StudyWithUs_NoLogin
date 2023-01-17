package com.jy.swu.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jy.swu.privateStudy.model.PrivateStudy;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<PrivateStudy> privateStudyList = new ArrayList<>();

    @Builder
    public Member(String username, String name, String email, String password, Role role){
        this.email = email;
        this.name = name;
        this.userName = username;
        this.password = password;
        this.role = role;
    }


}
