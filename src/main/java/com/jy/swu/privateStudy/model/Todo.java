package com.jy.swu.privateStudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "TodoName")
    private String name;

    @Column(nullable = false)
    private boolean complete;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PrivateStudy_Id")
    @JsonIgnore
    private PrivateStudy privateStudy;

    @Builder
    public Todo(String name, PrivateStudy privateStudy){
        this.name = name;
        this.complete = false;
        this.privateStudy = privateStudy;
    }


}
