package com.jy.swu.privateStudy.service;

import com.jy.swu.privateStudy.model.PrivateStudy;
import com.jy.swu.privateStudy.model.Todo;
import com.jy.swu.privateStudy.repository.PrivateStudyRepository;
import com.jy.swu.privateStudy.repository.TodoRepository;
import com.jy.swu.member.model.Member;
import com.jy.swu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrivateStudyService {

    private final PrivateStudyRepository studyRepository;
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public Member findUser(String userName){
        log.info("username: ",userName);
        Optional<Member> findMember = memberRepository.findByUserName(userName);
        Member member = findMember.get();
        log.info("member name1 : "+findMember);
        log.info("member name2 : "+findMember.get());
        if (findMember == null){
            throw new IllegalStateException("사용자가 없습니다.");
        }else{
            return member;
        }
    }

    public List<PrivateStudy> findStudyList(String userName){
        Member findMember = findUser(userName);
        return studyRepository.findByMember(findMember);
    }

    public void validDuplicated(String name){
        PrivateStudy validStudy = studyRepository.findByName(name);
        if(validStudy != null){
            throw new IllegalStateException("이름이 중복됩니다.");
        }
    }

    public void createStudy(String name, String userName){
        log.info("createStudy: "+userName);
        Member createMember = findUser(userName);
        validDuplicated(name);
        PrivateStudy createStudy = PrivateStudy.builder().name(name).member(createMember).build();
        studyRepository.save(createStudy);
    }

    public void createTodo(String todoName, String studyName){
        PrivateStudy findStudy = studyRepository.findByName(studyName);
        if (findStudy != null) {
            Todo createTodo = Todo.builder()
                    .name(todoName)
                    .privateStudy(findStudy)
                    .build();
            todoRepository.save(createTodo);
            findStudy.saveTodoList(createTodo);
            studyRepository.save(findStudy);
        }else{
            throw new IllegalStateException("스터디를 찾을 수 없습니다.");
        }

    }
}
