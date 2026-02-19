package fi.haagahelia.demo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import fi.haagahelia.demo.domain.Friend;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FriendController {
    private static final List<Friend> friends = new ArrayList<>();
    static {
        friends.add(new Friend("Minna","Minnanen"));
        friends.add(new Friend("Jukka","Jukkanen"));
        friends.add(new Friend("Pekka","Pekkanen"));
    }

    @GetMapping({"/","/friends"})
    public String showFriends(Model model){
        model.addAttribute("friends",friends);
        return "friends";
    }
     @GetMapping({"/add"})
     public String showAddForm(Model model){
        model.addAttribute("friend",new Friend());
        return "addfriend";
     }

     @PostMapping({"/add"})
     public String addFriend(@ModelAttribute Friend friend){
        if (friend.getFirstName() != null && !friend.getFirstName().trim().isEmpty()
        &&friend.getLastName() !=null && !friend.getLastName().trim().isEmpty()) {
            friends.add(new Friend(friend.getFirstName().trim(),friend.getLastName().trim()));
        }
        return "redirect:/friends";
     }
     
    

}
