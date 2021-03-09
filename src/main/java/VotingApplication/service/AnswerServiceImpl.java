package VotingApplication.service;

import VotingApplication.model.Answer;
import VotingApplication.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> allAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Answer read(int id) {
        return answerRepository.getOne(id);
    }

    @Override
    public List<Answer> answerByQuestion(int id) {
        return answerRepository.findByQuestionId(id);
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void delete(int id) {
        answerRepository.deleteById(id);
    }

    @Override
    public Answer getById(int id) {
        return answerRepository.getOne(id);
    }
}
