package lab.haze.SecurityMaster.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lab.haze.SecurityMaster.Model.UserBadge;
import lab.haze.SecurityMaster.Repository.UserBadgeRepository;
import lab.haze.SecurityMaster.Service.UserBadgeServiceImpl;

@Controller
public class BadgeController {
    
    @Autowired
    UserBadgeRepository userBadgeRepository;

    @Autowired
    UserBadgeServiceImpl userBadgeServiceImpl;

    @GetMapping("/badges")
    public String badges(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        UserBadge userBadge = userBadgeServiceImpl.getUserBadge(id);
        model.addAttribute("userBadge", userBadge);
        return "badges";

        
    }
}
