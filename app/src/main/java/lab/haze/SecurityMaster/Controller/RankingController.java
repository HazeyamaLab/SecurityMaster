package lab.haze.SecurityMaster.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String ranking(Model model) {
        List<User> list = new ArrayList<User>();
        list = userServiceImpl.getRanking();
        model.addAttribute("list", list);
        System.out.println(list);
        return "ranking";
    }
}
