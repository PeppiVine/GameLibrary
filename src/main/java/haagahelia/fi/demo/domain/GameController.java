package haagahelia.fi.demo.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class GameController {
    private final GameRepository gameRepository;
    private final UserReviewRepository userReviewRepository;
    private final AppUserRepository appUserRepository;

    public GameController(GameRepository gameRepository, UserReviewRepository userReviewRepository,
            AppUserRepository appUserRepository) {
        this.gameRepository = gameRepository;
        this.userReviewRepository = userReviewRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addGame(Model model) {
        model.addAttribute("game", new Game());
        return "addGame";
    }

    @PostMapping("/save")
    public String saveGame(@Valid @ModelAttribute Game game, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addgame";
        }
        gameRepository.save(game);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id) {
        gameRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editGame(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", gameRepository.findById(id).orElseThrow());

        return "editGame";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("/games/{id}")
    public String gameDetails(@PathVariable Long id, Model model) {

        Game game = gameRepository.findById(id).orElseThrow();
        model.addAttribute("game", game);
        model.addAttribute("reviews", userReviewRepository.findByGameId(id));
        model.addAttribute("userReview", new UserReview());
        return "game";
    }

    @PostMapping("/games/{id}/review")
    public String addReview(@PathVariable Long id, @ModelAttribute UserReview userReview,
            Authentication authentication) {

        Game game = gameRepository.findById(id).orElseThrow();
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElseThrow();

        userReview.setGame(game);
        userReview.setUser(user);
        userReviewRepository.save(userReview);
        return "redirect:/games/" + id;
    }

}
