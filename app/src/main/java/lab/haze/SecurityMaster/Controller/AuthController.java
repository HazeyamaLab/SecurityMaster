package lab.haze.SecurityMaster.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lab.haze.SecurityMaster.Model.User;
import lab.haze.SecurityMaster.Repository.UserRepository;
import lab.haze.SecurityMaster.Service.UserServiceImpl;

@Controller
public class AuthController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
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
    public String postRequest(@RequestParam("id") String id, @RequestParam("pass") String pass, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setId(id);
        System.out.println(encoder.encode(pass));
        user.setPassword(encoder.encode(pass));
        //UserRepository.registUser(user);

        return "regcon";
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/login-error";
    }
    
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";

        }
}
