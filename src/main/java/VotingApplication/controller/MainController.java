package VotingApplication.controller;

import VotingApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	private QuizService quizService;

	@Autowired
	public void setQuizService(QuizService quizService) {
		this.quizService = quizService;
	}

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("quizzes", quizService.listAll());
		return "home";
	}
}
