package VotingApplication.service;


import VotingApplication.model.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> allAnswers();
    Answer read(int id);
    List<Answer> answerByQuestion(int id);
    public void save(Answer answer);
	void delete(int id);
	Answer getById(int id);
}
