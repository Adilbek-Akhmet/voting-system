package VotingApplication.service;

import VotingApplication.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> allQuestions();
    Question read(int id);
    List<Question> questionByQuiz(int id);
    void save(Question question);
    int findCorrectAnswerId(int questionId);
}
