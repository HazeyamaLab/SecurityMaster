package lab.haze.SecurityMaster.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lab.haze.SecurityMaster.Model.User;
import lab.haze.SecurityMaster.Model.UserBadge;
import lab.haze.SecurityMaster.Repository.UserBadgeRepository;
import lab.haze.SecurityMaster.Repository.UserRepository;
import lab.haze.SecurityMaster.Service.UserBadgeServiceImpl;
import lab.haze.SecurityMaster.Service.UserServiceImpl;

@Controller
public class BadgeController {
    
    @Autowired
    UserBadgeRepository userBadgeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBadgeServiceImpl userBadgeServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/badges")
    public String badges(Model model,@AuthenticationPrincipal User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        model.addAttribute("userName", user.getName());
        UserBadge userBadge = userBadgeServiceImpl.getUserBadge(id);
        model.addAttribute("userBadge", userBadge);
        double currentUser = userRepository.findAll().size();
        currentUser = 8;
        List<Double> persentList = new ArrayList<Double>();
        List<UserBadge> userBadgeList = new ArrayList<>();
        userBadgeList = userBadgeServiceImpl.findAll();
        double hasBadge1 = 0;
        double hasBadge2 = 0;
        double hasBadge3 = 0;
        double hasBadge4 = 0;
        double hasBadge5 = 0;
        double hasBadge6 = 0;
        double hasBadge7 = 0;
        double hasBadge8 = 0;
        double hasBadge9 = 0;
        double hasBadge10 = 0;
        double hasBadge11 = 0;
        double hasBadge12 = 0;
        double hasBadge13 = 0;
        double hasBadge14 = 0;
        double hasBadge15 = 0;
        double hasBadge16 = 0;
        double hasBadge17 = 0;
        double hasBadge18 = 0;
        double hasBadge19 = 0;
        double hasBadge20 = 0;
        double hasBadge21 = 0;
        double hasBadge22 = 0;
        for(int i = 0;i < currentUser;i++){
            if(userBadgeList.get(i).isBadge1()){
                hasBadge1 ++;
            }
            if(userBadgeList.get(i).isBadge2()){
                hasBadge2 ++;
            }
            if(userBadgeList.get(i).isBadge3()){
                hasBadge3 ++;
            }
            if(userBadgeList.get(i).isBadge4()){
                hasBadge4 ++;
            }
            if(userBadgeList.get(i).isBadge5()){
                hasBadge5 ++;
            }
            if(userBadgeList.get(i).isBadge6()){
                hasBadge6 ++;
            }
            if(userBadgeList.get(i).isBadge7()){
                hasBadge7 ++;
            }
            if(userBadgeList.get(i).isBadge8()){
                hasBadge8 ++;
            }
            if(userBadgeList.get(i).isBadge9()){
                hasBadge9 ++;
            }
            if(userBadgeList.get(i).isBadge10()){
                hasBadge10 ++;
            }
            if(userBadgeList.get(i).isBadge11()){
                hasBadge11 ++;
            }
            if(userBadgeList.get(i).isBadge12()){
                hasBadge12 ++;
            }
            if(userBadgeList.get(i).isBadge13()){
                hasBadge13 ++;
            }
            if(userBadgeList.get(i).isBadge14()){
                hasBadge14 ++;
            }
            if(userBadgeList.get(i).isBadge15()){
                hasBadge15 ++;
            }
            if(userBadgeList.get(i).isBadge16()){
                hasBadge16 ++;
            }
            if(userBadgeList.get(i).isBadge17()){
                hasBadge17 ++;
            }
            if(userBadgeList.get(i).isBadge18()){
                hasBadge18 ++;
            }
            if(userBadgeList.get(i).isBadge19()){
                hasBadge19 ++;
            }
            if (userBadgeList.get(i).isBadge20()) {
                hasBadge20++;
            }
            if(userBadgeList.get(i).isBadge21()){
                hasBadge21 ++;
            }
            if(userBadgeList.get(i).isBadge22()){
                hasBadge22 ++;
            }
            
        }
        persentList.add((double)Math.round(((hasBadge1/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge2/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge3/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge4/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge5/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge6/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge7/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge8/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge9/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge10/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge11/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge12/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge13/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge14/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge15/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge16/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge17/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge18/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge19/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge20/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge21/currentUser)*100) * 100)/100);
        persentList.add((double)Math.round(((hasBadge22/currentUser)*100) * 100)/100);

    model.addAttribute("list", persentList);
    return "badges";
    }
}
