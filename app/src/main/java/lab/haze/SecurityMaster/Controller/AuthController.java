package lab.haze.SecurityMaster.Controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.net.SyslogOutputStream;
import javassist.expr.NewArray;
import lab.haze.SecurityMaster.Model.BadgeTimeline;
import lab.haze.SecurityMaster.Model.BadgeTimelineDetail;
import lab.haze.SecurityMaster.Model.User;
import lab.haze.SecurityMaster.Model.UserBadge;
import lab.haze.SecurityMaster.Repository.UserBadgeRepository;
import lab.haze.SecurityMaster.Repository.UserRepository;
import lab.haze.SecurityMaster.Service.BadgeTimelineServiceImpl;
import lab.haze.SecurityMaster.Service.UserServiceImpl;

@Controller
public class AuthController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBadgeRepository userBadgeRepository;

    @Autowired
    BadgeTimelineServiceImpl badgeTimelineServiceImpl;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginfai")
    public String loginfai() {
        return "loginfai";
    }
    
    @GetMapping("/menu")
    public String menu(Model model,@AuthenticationPrincipal User user) {
        //System.out.println(user);
        model.addAttribute("user", user);
        List<BadgeTimeline> list = badgeTimelineServiceImpl.getAll();
        List<BadgeTimelineDetail> detail_list = new ArrayList<BadgeTimelineDetail>();
        for(int c = 0; c < list.size();c++){
            BadgeTimeline badgeTimeline =list.get(c);
            BadgeTimelineDetail badgeTimelineDetail = new BadgeTimelineDetail();
            int badgeId = badgeTimeline.getBadgeId();
            String badgeName = "Hello Security!";
            if(badgeId == 1){
                badgeName = "Hello Security!";
            }else if(badgeId == 2){
                badgeName = "インジェクションの学習者";
            }else if(badgeId == 3){
                badgeName = "PlaceHolder";
            }else if(badgeId == 4){
                badgeName = "ディレクトリの学習者";
            }else if(badgeId == 5){
                badgeName = "secret.txt";
            }else if(badgeId == 6){
                badgeName = "セッションの学習者";
            }else if(badgeId == 7){
                badgeName = "session.invalidate()";
            }else if(badgeId == 8){
                badgeName = "XSSの学習者";
            }else if(badgeId == 9){
                badgeName = "document.cookie";
            }
            
            badgeTimelineDetail.setBadgeName(badgeName);
            String userId = badgeTimeline.getUserId();
            String userName = userRepository.getById(userId).getName();
            badgeTimelineDetail.setUserName(userName);
            badgeTimelineDetail.setLtd(badgeTimeline.getLtd());

            detail_list.add(badgeTimelineDetail);
        }
        model.addAttribute("list", detail_list);
        return "menu";
    }

    @GetMapping("/regist")
    public String regist() {
        return "regist";
    }
    @GetMapping("/getUser")
    public String getUser() {
        //repository
        List<User> test = userServiceImpl.getUsers();
        System.out.println(test);
        return "regist";
    }

    @PostMapping("/regist")
    public String postRequest(@RequestParam("id") String id, @RequestParam("pass") String pass, @RequestParam("name") String name ,@RequestParam("cName") String cName, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean isAccountExists = userRepository.existsById(id);
        //System.out.println(existUser);
        if(isAccountExists){
            return "useralreadyexists";
        }else{
            User user = new User();
            UserBadge userBadge = new UserBadge();
            user.setId(id);
            System.out.println(encoder.encode(pass));
            user.setPassword(encoder.encode(pass));
            user.setCompanyName(cName);
            user.setCompanyWorth(1000000);
            user.setName(name);
            user.setRole("USER");
            userBadge.setId(id);
            userRepository.save(user);
            userBadgeRepository.save(userBadge);
            return "regcon";
        }
    }

    @PostMapping("/login")
    public String postRequest(@RequestParam("username") String id, @RequestParam("password") String pass,Model model) {
        return "/login";
    }
    
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";

        }
}
