package lab.haze.SecurityMaster.Controller;

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
import lab.haze.SecurityMaster.Model.User;
import lab.haze.SecurityMaster.Model.UserBadge;
import lab.haze.SecurityMaster.Repository.UserBadgeRepository;
import lab.haze.SecurityMaster.Repository.UserRepository;
import lab.haze.SecurityMaster.Service.UserServiceImpl;

@Controller
public class AuthController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBadgeRepository userBadgeRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    
    @GetMapping("/menu")
    public String menu(Model model,@AuthenticationPrincipal User user ) {
        System.out.println(user);
        model.addAttribute("user", user);
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
            user.setCompanyWorth(10000);
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
