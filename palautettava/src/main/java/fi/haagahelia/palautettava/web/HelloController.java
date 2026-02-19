package fi.haagahelia.palautettava.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public record HelloController() {
@RequestMapping("/hello")
	public String greeting(@RequestParam(name="name") String name, Model model)    {
		model.addAttribute("name", name);
		return "hello"; // hello.html
	}

	@RequestMapping("/hellothymeleaf")
	public String hellothymeleaf( 
		@RequestParam String name,
		@RequestParam int age,
		Model model){
			model.addAttribute("name",name);
			model.addAttribute("age", age);
			return "hellothymeleaf";
		}

}
