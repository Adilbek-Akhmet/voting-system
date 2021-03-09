package VotingApplication.service;


import VotingApplication.model.Answer;
import VotingApplication.model.Question;
import VotingApplication.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> allQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question read(int id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public List<Question> questionByQuiz(int id) {
        return questionRepository.findByQuizId(id);
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public int findCorrectAnswerId(int questionId) {
        Question question = questionRepository.findById(questionId).get();
        for (Answer answer : question.getAnswers()) {
            if (answer.getCorrect() == 1) {
                return answer.getId();
            }
        }
        return -1;
    }

    @Override
    public Question getById(int id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        questionRepository.deleteById(id);
    }
}
