package VotingApplication.controller;

import VotingApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public String home(Model model) {
		model.addAttribute("quizzes", quizService.listAll());
		return "home";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String admin() {
		return "admin";
	}
}
