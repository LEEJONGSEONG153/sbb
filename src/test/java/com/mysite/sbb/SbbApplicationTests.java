package com.mysite.sbb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa() {
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가?");
//        q1.setContent("sbb에 대해서 알고 싶따");
//        q1.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q1);
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문임");
//        q2.setContent("id는 자동 생성?");
//        q2.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q2);
    }

	@Test
	void testJpa2() {
        List<Question> all = questionRepository.findAll();
        assertEquals(2,all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가?",q.getSubject());
	}

    @Test
    void testJpa3() {

        Optional<Question> oq = questionRepository.findById(1);

        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가?",q.getSubject());
        }
    }

    @Test
    void testJpa4() {
        Question q = questionRepository.findBySubject("sbb가 무엇인가?");
        assertEquals(3,q.getId());
    }

    @Test
    void testJpa5() {
        Question q = questionRepository.findBySubjectAndContent("sbb가 무엇인가?","sbb에 대해서 알고 싶따");
        assertEquals(3,q.getId());
    }
}
