package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가?");
        q1.setContent("sbb에 대해서 알고 싶따");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문임");
        q2.setContent("id는 자동 생성?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);
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

    @Test
    void testJpa6() {
        List<Question> qList = questionRepository.findBySubjectLike("sbb%");
        assertEquals("sbb가 무엇인가?",qList.get(0).getSubject());
    }

    @Test
    void testJpa7() {
        Optional<Question> oq = questionRepository.findById(3);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        questionRepository.save(q);
    }

    @Test
    void testJpa8() {
        assertEquals(2,questionRepository.count());
        Optional<Question> oq = questionRepository.findById(3);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        questionRepository.delete(q);
        assertEquals(1,questionRepository.count());
    }

    @Test
    void testJpa9() {
        Optional<Question> oq = questionRepository.findById(4);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);   //어떤질문의 답변인지 Question객체 넣어줌
        a.setCreateDate(LocalDateTime.now());
        answerRepository.save(a);
    }

    @Test
    void testJpa10() {
        Optional<Answer> oa = answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(4,a.getQuestion().getId());
    }

    @Transactional
    @Test
    void testJpa11() {
        Optional<Question> oq = questionRepository.findById(4);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1,answerList.size());
        assertEquals("네 자동으로 생성됩니다.",answerList.get(0).getContent());

    }

}
