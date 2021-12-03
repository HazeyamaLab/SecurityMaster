package lab.haze.SecurityMaster.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lab.haze.SecurityMaster.Model.User;
import lab.haze.SecurityMaster.Model.UserBadge;
import lab.haze.SecurityMaster.Repository.UserRepository;
import lab.haze.SecurityMaster.Service.BadgeTimelineService;
import lab.haze.SecurityMaster.Service.UserBadgeServiceImpl;
import lab.haze.SecurityMaster.Service.UserServiceImpl;
@Controller
public class LearnController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserBadgeServiceImpl userBadgeServiceImpl;

    @Autowired
    BadgeTimelineService badgeTimelineService;

    @Autowired
    HttpSession httpSession;



    @GetMapping("/learn/prologue")
    public String prologue(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("userName", user.getName());
        model.addAttribute("companyName", user.getCompanyName());
        return "learn/prologue";
    }
    @GetMapping("/learn")
    public String learn(){
        return "/learn";
    }

    @GetMapping("/learn/1injection/intro")
    public String learn1(){
        return "/learn/1injection/intro";
    }

    @GetMapping("/learn/1injection/lecture")
    public String lecture1(){
        return "/learn/1injection/lecture";
    
    }

    @GetMapping("/learn/1injection/test1")
    public String injtest1(){
        return "/learn/1injection/test1";
    }

    @PostMapping("/learn/1injection/test1")
    public String postTest1(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 4) {
            httpSession.setAttribute("colCount", "0");
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "/learn/1injection/inc1";
        } else {
            httpSession.setAttribute("colCount", "1");
            double worth = user.getCompanyWorth();
            worth = worth * 1.2;
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "/learn/1injection/col1";
        }
    }
    
    @GetMapping("/learn/1injection/inc1")
    public String injInc1(@AuthenticationPrincipal User user){
        //企業価値変更のコード
        return "/learn/1injection/inc1";
    }

    @GetMapping("/learn/1injection/col1")
    public String injCol1(@AuthenticationPrincipal User user) {
        //企業価値変更のコード
        return "/learn/1injection/col1";
    }
    
    @PostMapping("/learn/1injection/test2")
    public String postTest2(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 4) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "/learn/1injection/inc2";
        } else {
            double worth = user.getCompanyWorth();
            worth = worth * 1.2;
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            return "/learn/1injection/col2";
        }
    }
    @GetMapping("/learn/1injection/test2")
    public String injtest2(){
        return "/learn/1injection/test2";
    }

    @GetMapping("/learn/1injection/inc2")
    public String injInc2(@AuthenticationPrincipal User user){
        //企業価値変更のコード
        return "/learn/1injection/inc2";
    }

    @GetMapping("/learn/1injection/col2")
    public String injCol2(@AuthenticationPrincipal User user) {
        //企業価値変更のコード
        return "/learn/1injection/col2";
    }

    @GetMapping("/learn/1injection/fin")
    public String injfin(Model model,@AuthenticationPrincipal User user){
        Object c =  httpSession.getAttribute("colCount");
        String cs = c.toString();
        int count = Integer.parseInt(cs);
        UserBadge userBadge = userBadgeServiceImpl.getUserBadge(user.getId());
        userBadge.setBadge1(true);
        if(count == 2){
            userBadge.setBadge2(true);
        }
        userBadgeServiceImpl.updateBadge(userBadge);
        httpSession.invalidate();
        return "/learn/1injection/fin";
    }

    


    

    
}
