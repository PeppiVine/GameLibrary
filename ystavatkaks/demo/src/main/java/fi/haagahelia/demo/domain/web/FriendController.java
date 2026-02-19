package fi.haagahelia.demo.domain.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.demo.domain.Friend;

@Controller
public class FriendController {
private static final List<Friend> friends= new ArrayList<>();
static {
    friends.add(new Friend("Pekka","Pekkanen"));
    friends.add(new Friend("Pekka2","Pekkanen2"));
    friends.add(new Friend("Pekka3","Pekkanen3"));
}

    @GetMapping({"/","/friends"})
    public String showFriends(Model model){
        model.addAttribute("friends",friends);
        model.addAttribute("friend",new Friend());
        return "friends";
    }
    
    @PostMapping("/friends")
    public String addFriend (@ModelAttribute Friend friend){
        if (friend.getFirstName() !=null && !friend.getFirstName().trim().isEmpty()
        && friend.getLastName() !=null && !friend.getLastName().trim().isEmpty()) {
            friends.add(new Friend(friend.getFirstName().trim(),friend.getLastName().trim()));
    
        }
        return "redirect:/friends";
    }
}
