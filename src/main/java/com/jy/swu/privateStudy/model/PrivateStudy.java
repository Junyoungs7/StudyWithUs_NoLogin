package com.jy.swu.privateStudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jy.swu.member.model.Member;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrivateStudy {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "PrivateStudyId", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, name = "PrivateStudyName")
    private String name;

    @OneToMany(mappedBy = "privateStudy")
    private List<Todo> todoList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Users_userName")
    @JsonIgnore
    private Member member;

    @Builder
    public PrivateStudy(String name, Member member){
        this.name = name;
        this.member = member;
    }

    public PrivateStudy saveTodoList(Todo todo){
        todoList.add(todo);
        return null;
    }

}
