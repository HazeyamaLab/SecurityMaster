package lab.haze.SecurityMaster.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lab.haze.SecurityMaster.Model.User;
import lab.haze.SecurityMaster.Service.UserServiceImpl;

@Controller
public class RankingController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/ranking")
    public String ranking(Model model,@AuthenticationPrincipal User user) {
        List<User> list = new ArrayList<User>();
        list = userServiceImpl.getRanking();
        model.addAttribute("list", list);
        int count = 1;
        for(User listedUser : list){
            if(listedUser.getId().equals(user.getId())){
                break;
            }else{
                count ++;
            }
        }
        model.addAttribute("userRank", count);
        model.addAttribute("user", user);
        return "ranking";
    }
}
