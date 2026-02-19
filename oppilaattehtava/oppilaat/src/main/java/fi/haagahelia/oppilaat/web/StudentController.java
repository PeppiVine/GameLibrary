package fi.haagahelia.oppilaat.web;

import fi.haagahelia.oppilaat.web.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    @RequestMapping("/hello")
    public String hello(Model model) {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Akke", "Bajgora"));
        students.add(new Student("Karri", "Haaparinne"));
        students.add(new Student("Kirsi", "Vine"));
        model.addAttribute("students", students);
        return "hello";

    }
}
