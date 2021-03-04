package VotingApplication.repository;

import VotingApplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	//@Query("select q from Questions q where q.quizId = ?1")
	public List<Question> findByQuizId(int quizId);
}