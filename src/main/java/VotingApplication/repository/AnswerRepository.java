package VotingApplication.repository;

import VotingApplication.model.Answer;
import VotingApplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	public List<Answer> findByQuestionId(int questionId);
}