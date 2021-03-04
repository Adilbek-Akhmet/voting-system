package VotingApplication.service;

import VotingApplication.model.Quiz;

import java.util.List;

public interface QuizService {
    public void save(Quiz quiz);
    public List<Quiz> listAll();
    public Quiz getById(int id);
    public void delete(int id);
}
