package lab.haze.SecurityMaster.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import lab.haze.SecurityMaster.Repository.BadgeTimelineRepository;
import lab.haze.SecurityMaster.Repository.UserBadgeRepository;
import lab.haze.SecurityMaster.Repository.UserRepository;
import lab.haze.SecurityMaster.Service.BadgeTimelineServiceImpl;
import lab.haze.SecurityMaster.Service.UserBadgeServiceImpl;
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
    UserBadgeServiceImpl userBadgeServiceImpl;

    @Autowired
    BadgeTimelineServiceImpl badgeTimelineServiceImpl;

    @Autowired
    BadgeTimelineRepository badgeTimelineRepository;

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
        UserBadge userBadge = new UserBadge();
        userBadge = userBadgeServiceImpl.getUserBadge(user.getId());

        if (!userBadge.isBadge18() && user.getCompanyWorth() >= 3000000) {
            userBadge.setBadge18(true);
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            badgeTimeline.setBadgeId(18);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
        }
        if (!userBadge.isBadge19() && user.getCompanyWorth() >= 6000000) {
            userBadge.setBadge19(true);
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            badgeTimeline.setBadgeId(19);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
        }
        if (!userBadge.isBadge20() && user.getCompanyWorth() >= 9000000) {
            userBadge.setBadge20(true);
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            badgeTimeline.setBadgeId(20);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
        }
        if (!userBadge.isBadge21() && user.getCompanyWorth() >= 12000000) {
            userBadge.setBadge21(true);
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            badgeTimeline.setBadgeId(21);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
        }
        if (userBadge.isBadge1() && userBadge.isBadge2() && userBadge.isBadge3() && userBadge.isBadge4()
                && userBadge.isBadge5() && userBadge.isBadge6() && userBadge.isBadge7() && userBadge.isBadge8()
                && userBadge.isBadge9() && userBadge.isBadge10() && userBadge.isBadge11() && userBadge.isBadge12()
                && userBadge.isBadge13() && userBadge.isBadge14() && userBadge.isBadge15() && userBadge.isBadge16()
                && userBadge.isBadge17() && userBadge.isBadge18() && userBadge.isBadge19() && userBadge.isBadge20()
                && userBadge.isBadge21()) {
                    userBadge.setBadge22(true);
                    BadgeTimeline badgeTimeline = new BadgeTimeline();
                    badgeTimeline.setBadgeId(22);
                    badgeTimeline.setUserId(user.getId());
                    LocalDateTime now = LocalDateTime.now();
                    now.plusHours(9);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    badgeTimeline.setLtd(now.format(formatter));
                    badgeTimelineRepository.save(badgeTimeline);
                    badgeTimelineRepository.flush();
        }
        if (userBadge.isBadge1() && userBadge.isBadge2() &&  userBadge.isBadge4()
                 && userBadge.isBadge6()  && userBadge.isBadge8()
                 && userBadge.isBadge10() && userBadge.isBadge12() && userBadge.isBadge14() ) {
                    userBadge.setBadge16(true);
                    BadgeTimeline badgeTimeline = new BadgeTimeline();
                    badgeTimeline.setBadgeId(16);
                    badgeTimeline.setUserId(user.getId());
                    LocalDateTime now = LocalDateTime.now();
                    now.plusHours(9);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    badgeTimeline.setLtd(now.format(formatter));
                    badgeTimelineRepository.save(badgeTimeline);
                    badgeTimelineRepository.flush();
        }
        if (userBadge.isBadge1() && userBadge.isBadge2() && userBadge.isBadge3() && userBadge.isBadge4()
                && userBadge.isBadge5() && userBadge.isBadge6() && userBadge.isBadge7() && userBadge.isBadge8()
                && userBadge.isBadge9() && userBadge.isBadge10() && userBadge.isBadge11() && userBadge.isBadge12()
                && userBadge.isBadge13() && userBadge.isBadge14() && userBadge.isBadge15()) {
                    userBadge.setBadge17(true);
                    BadgeTimeline badgeTimeline = new BadgeTimeline();
                    badgeTimeline.setBadgeId(17);
                    badgeTimeline.setUserId(user.getId());
                    LocalDateTime now = LocalDateTime.now();
                    now.plusHours(9);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    badgeTimeline.setLtd(now.format(formatter));
                    badgeTimelineRepository.save(badgeTimeline);
                    badgeTimelineRepository.flush();
        }

        model.addAttribute("user", user);
        boolean isBadgeExists = badgeTimelineRepository.existsById(4);
        if (isBadgeExists) {
            List<BadgeTimeline> list = badgeTimelineServiceImpl.getAll();
            List<BadgeTimelineDetail> detail_list = new ArrayList<BadgeTimelineDetail>();
            for (int c = 0; c < list.size(); c++) {
                BadgeTimeline badgeTimeline = list.get(c);
                BadgeTimelineDetail badgeTimelineDetail = new BadgeTimelineDetail();
                int badgeId = badgeTimeline.getBadgeId();
                String badgeName = "Hello Security!";
                if (badgeId == 1) {
                    badgeName = "Hello Security!";
                } else if (badgeId == 2) {
                    badgeName = "????????????????????????????????????";
                } else if (badgeId == 3) {
                    badgeName = "PlaceHolder";
                } else if (badgeId == 4) {
                    badgeName = "??????????????????????????????";
                } else if (badgeId == 5) {
                    badgeName = "secret.txt";
                } else if (badgeId == 6) {
                    badgeName = "???????????????????????????";
                } else if (badgeId == 7) {
                    badgeName = "session.invalidate()";
                } else if (badgeId == 8) {
                    badgeName = "XSS????????????";
                } else if (badgeId == 9) {
                    badgeName = "document.cookie";
                } else if (badgeId == 10) {
                    badgeName = "CSRF????????????";
                } else if (badgeId == 11) {
                    badgeName = "Password,please";
                } else if (badgeId == 12) {
                    badgeName = "HTTP ?????????????????????????????????????????????";
                } else if (badgeId == 13) {
                    badgeName = "Set-Cookie-Click ";
                } else if (badgeId == 14) {
                    badgeName = "??????????????????????????????????????????";
                } else if (badgeId == 15) {
                    badgeName = "Keyboard and Mouse";
                } else if (badgeId == 16) {
                    badgeName = "????????????";
                } else if (badgeId == 17) {
                    badgeName = "???????????????????????????????????????????????????";
                } else if (badgeId == 18) {
                    badgeName = "????????????";
                } else if (badgeId == 19) {
                    badgeName = "??????????????????";
                } else if (badgeId == 20) {
                    badgeName = "2?????????";
                } else if (badgeId == 21) {
                    badgeName = "1?????????";
                } else if (badgeId == 22) {
                    badgeName = "SecurityMaster";
                }
                badgeTimelineDetail.setBadgeName(badgeName);
                String userId = badgeTimeline.getUserId();
                String userName = userRepository.getById(userId).getName();
                badgeTimelineDetail.setUserName(userName);
                badgeTimelineDetail.setLtd(badgeTimeline.getLtd());

                detail_list.add(badgeTimelineDetail);
            }
            model.addAttribute("list", detail_list);
        }
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
