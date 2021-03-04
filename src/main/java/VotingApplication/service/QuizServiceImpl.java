package VotingApplication.service;


import VotingApplication.model.Quiz;
import VotingApplication.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> listAll() {
        return (List<Quiz>) quizRepository.findAll();
    }

    @Override
    public Quiz getById(int id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        quizRepository.deleteById(id);
    }


}
