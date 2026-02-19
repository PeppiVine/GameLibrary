package fi.haagahelia.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to Bookstore");
        return "index" ;
    }
}
