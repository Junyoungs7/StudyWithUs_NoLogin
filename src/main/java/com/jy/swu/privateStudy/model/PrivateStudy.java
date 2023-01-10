package com.jy.swu.privateStudy.model;

import com.jy.swu.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrivateStudy {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "PrivateStudyId", columnDefinition = "BINARY(16)")
    private String id;

    @Column(nullable = false, name = "PrivateStudyName")
    private String name;

    @Column(nullable = false)
    @OneToMany(mappedBy = "privateStudy")
    private List<Todo> todoList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Users_Id")
    private User user;


}
