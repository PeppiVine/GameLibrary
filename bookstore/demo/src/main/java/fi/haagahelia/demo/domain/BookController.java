package fi.haagahelia.demo.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
private final BookRepository bookRepository;

public BookController(BookRepository bookRepository){
    this.bookRepository=bookRepository;
}

@GetMapping("/booklist")
public String bookList(Model model){
    model.addAttribute("books", bookRepository.findAll());
    return "booklist";
}
}
