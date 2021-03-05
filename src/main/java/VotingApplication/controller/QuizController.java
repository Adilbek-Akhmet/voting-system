package VotingApplication.controller;

import VotingApplication.model.Answer;
import VotingApplication.model.Question;
import VotingApplication.model.Quiz;
import VotingApplication.service.AnswerService;
import VotingApplication.service.QuestionService;
import VotingApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

	private QuizService quizService;
	private QuestionService questionService;
	private AnswerService answerService;

	@Autowired
	public void setQuizService(QuizService quizService) {
		this.quizService = quizService;
	}
	@Autowired
	public void setQuestionService(QuestionService questionService) {this.questionService = questionService; }
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}


	@GetMapping("/addQuiz")
	public String addQuizPage(Model model) {
		model.addAttribute("act", "Save");
		model.addAttribute("quiz", new Quiz());
		return "addQuizPage";
	}

	@PostMapping("/addQuiz")
	public String addQuiz(@ModelAttribute("quiz") Quiz quiz) {
		quizService.save(quiz);
		return "redirect:/quiz/addQuestion/"+quiz.getId();
	}

	@GetMapping("/addQuestion/{quizId}")
	public String addQuestionPage(@PathVariable("quizId") int quizId, Model model) {
		model.addAttribute("question", new Question());
		return "addQuestion";
	}

	@PostMapping("/addQuestion/{quizId}")
	public String addQuestion(@PathVariable("quizId") int quizId,
	                          @ModelAttribute("question") Question question,
	                          Model model) {
		question.setQuiz(quizService.getById(quizId));
		questionService.save(question);
		return "redirect:/quiz/addQuestion/{quizId}/"+ question.getId();
	}

	@GetMapping("/addQuestion/{quizId}/{questionId}")
	public String addAnswerPage(@PathVariable("quizId") int quizId,
	                            @PathVariable("questionId") int questionId,
	                            Model model) {
		model.addAttribute("answer", new Answer());
		model.addAttribute("question", questionService.read(questionId));
		return "addAnswerPage";
	}

	@PostMapping("/addQuestion/{quizId}/{questionId}")
	public String addAnswer(@PathVariable("quizId") int quizId,
	                        @PathVariable("questionId") int questionId,
	                        @ModelAttribute("answer") Answer answer,
	                        Model model) {
		answer.setQuestion(questionService.read(questionId));
		answerService.save(answer);
		return "redirect:/quiz/addQuestion/{quizId}/{questionId}";
	}

	@GetMapping("/allQuizzes")
	public String allQuizzesPage(Model model) {
		model.addAttribute("quizzes", quizService.listAll());
		return "allQuizzes";
	}

	@GetMapping("/quizzes/{id}")
	public String runQuiz(@PathVariable("id") int id, Model model) {
		List<Question> questions = questionService.questionByQuiz(id);
		model.addAttribute("questions", questions);
		return "questions";
	}

	@PostMapping("/quizzes/{id}")
	public String submit(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		String[] questionIds = request.getParameterValues("questionId");
		int score = 0;
		for (String questionId : questionIds) {
			int correctAnswerId = questionService.findCorrectAnswerId(Integer.parseInt(questionId));
			if (correctAnswerId == Integer.parseInt(request.getParameter("question_"+questionId))) {
				score++;
			}
		}
		model.addAttribute("score", score);
		return "result";
	}

	@GetMapping("/delete/{id}")
	public String deleteQuiz(@PathVariable("id") int id) {
		quizService.delete(id);
		return "redirect:/quiz/allQuizzes";
	}

	@GetMapping("/edit/{id}")
	public String editQuizPage(@PathVariable("id") int id, Model model) {
		model.addAttribute("quiz", quizService.getById(id));
		model.addAttribute("quizId", id);
		model.addAttribute("act", "Edit");
		return "addQuizPage";
	}

	@PostMapping("/editQuiz")
	public String editQuiz(@ModelAttribute("quiz") Quiz quiz) {
		quizService.save(quiz);
		return "redirect:/quiz/allQuizzes";
	}


}